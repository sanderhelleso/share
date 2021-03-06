package com.semanta.share.service.share;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.semanta.share.exception.DirNotFoundException;
import com.semanta.share.exception.MyFileNotFoundException;
import com.semanta.share.exception.StorageFullException;
import com.semanta.share.exception.UploadFailedException;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.repository.ShareInfoRepository;
import com.semanta.share.utils.DelDirTask;
import com.semanta.share.utils.FileInfo;
import com.semanta.share.utils.FileSystem;
import com.semanta.share.utils.LookupIP;
import com.semanta.share.utils.Share;
import com.semanta.share.utils.ZipDir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    @Override
    public String upload(MultipartFile[] files, HttpServletRequest request) {

        // ensure storage has room for new upload
        if (!FileSystem.canUpload(files)) {
            String errMsg = "Our service currently has alot of traffic and we are out of storage space to handle your uploads!";
            throw new StorageFullException(errMsg);
        }

        try {
            String dirID = FileSystem.makeTmpDir();
            String country = LookupIP.lookup(request.getRemoteAddr());

            FileSystem.uploadFiles(files, dirID);
            new DelDirTask(FileSystem.concatDirs(dirID));
            this.saveShareInfo(dirID, country);

            return dirID;
        } catch (Exception e) {
            throw new UploadFailedException("Unable to upload files. Please try again");
        }
    }

    @Override
    public Share retrieve(String dirID, HttpServletRequest request) {
        Optional<ShareInfo> shareInfoOpt = shareInfoRepo.findById(dirID);

        if (!shareInfoOpt.isPresent()) {
            throw new DirNotFoundException("Directory has expired or might have never existed");
        }

        String downloadUrl = FileSystem.concatDirs(dirID);
        ShareInfo shareInfo = this.updateShareInfo(shareInfoOpt, request);
        List<FileInfo> files = FileSystem.getFilesFromDir(dirID);

        return new Share(downloadUrl, shareInfo, files);
    }

    @Override
    public ResponseEntity<Resource> download(String fileName) {
        String errMsg = "Unable to download file cause it no longer exists or never had";

        try {
            String filePath;
            Resource resource;

            // check if root dir, make & send zip if true
            Optional<ShareInfo> shareInfoOpt = shareInfoRepo.findById(fileName);
            if (shareInfoOpt.isPresent()) {
                filePath = ZipDir.zip(fileName);
            } else {
                filePath = FileSystem.concatDirs(fileName);
            }

            resource = new UrlResource(FileSystem.getUri(filePath));

            if (!resource.exists()) {
                throw new MyFileNotFoundException(errMsg);
            }

            String contentType = FileSystem.getMimeType(resource.getFile());
            String header = "attachment; filename=\"" + resource.getFilename() + "\"";

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, header).body(resource);

        } catch (IOException e) {
            throw new MyFileNotFoundException(errMsg + ": " + fileName, e);
        }
    }

    @Override
    public List<ShareInfo> retrieveAll() {
        return shareInfoRepo.findAll();
    }

    private void saveShareInfo(String dirID, String country) {
        ShareInfo shareInfo = new ShareInfo(dirID, country);
        shareInfoRepo.save(shareInfo);
    }

    private ShareInfo updateShareInfo(Optional<ShareInfo> shareInfoOpt, HttpServletRequest request) {
        String country = LookupIP.lookup(request.getRemoteAddr());

        ShareInfo shareInfo = shareInfoOpt.get();

        shareInfo.setLastDownloadedAt();
        shareInfo.setTotDownloads();
        shareInfo.addShareAccessedFrom(country);

        shareInfoRepo.save(shareInfo);

        return shareInfo;
    }
}