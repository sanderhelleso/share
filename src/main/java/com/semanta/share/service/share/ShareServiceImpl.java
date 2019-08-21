package com.semanta.share.service.share;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.semanta.share.utils.FileInfo;
import com.semanta.share.utils.FileSystem;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.repository.ShareInfoRepository;
import com.semanta.share.utils.DelDirTask;
import com.semanta.share.utils.LookupIP;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    @Override
    public String upload(MultipartFile[] files, long timeout, HttpServletRequest request) {
        String dirID = FileSystem.makeTmpDir();

        String country = LookupIP.lookup(request.getRemoteAddr());
        this.saveShareInfo(dirID, timeout, country);
        new DelDirTask(timeout, FileSystem.concatDirs(dirID));
        return dirID;
    }

    @Override
    public List<FileInfo> retrieve(String dirID, HttpServletRequest request) {
        Optional<ShareInfo> shareInfoOpt = shareInfoRepo.findById(dirID);

        try {
            shareInfoOpt.orElseThrow(() -> new Exception("Directory has expired or might have never existed"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.updateShareInfo(shareInfoOpt, request);

        File dir = new File(FileSystem.concatDirs(dirID));
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();

        for (File file : dir.listFiles()) {
            String name = file.getName();
            String dlPath = file.getPath();
            String type = FileSystem.getMimeType(file);
            long size = FileSystem.getSize(file);

            files.add(new FileInfo(name, type, dlPath, size));
        }

        return files;
    }

    @Override
    public List<ShareInfo> retrieveAll() {
        return shareInfoRepo.findAll();
    }

    private void saveShareInfo(String dirID, Long timeout, String country) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + timeout);

        ShareInfo shareInfo = new ShareInfo(dirID, expiresAt, country);
        shareInfoRepo.save(shareInfo);
    }

    private void updateShareInfo(Optional<ShareInfo> shareInfoOpt, HttpServletRequest request) {
        String country = LookupIP.lookup(request.getRemoteAddr());

        ShareInfo shareInfo = shareInfoOpt.get();
        shareInfo.setLastDownloadedAt();
        shareInfo.setTotDownloads();
        shareInfo.addShareAccessedFrom(country);
        shareInfoRepo.save(shareInfo);
    }
}