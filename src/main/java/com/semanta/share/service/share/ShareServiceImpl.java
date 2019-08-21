package com.semanta.share.service.share;

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
import com.semanta.share.utils.Share;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    @Override
    public String upload(MultipartFile[] files, long timeout, HttpServletRequest request) {
        String dirID = FileSystem.makeTmpDir();
        String country = LookupIP.lookup(request.getRemoteAddr());

        try {
            FileSystem.uploadFiles(files, dirID);
            new DelDirTask(timeout, FileSystem.concatDirs(dirID));
            this.saveShareInfo(dirID, timeout, country);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dirID;
    }

    @Override
    public Share retrieve(String dirID, HttpServletRequest request) {
        Optional<ShareInfo> shareInfoOpt = shareInfoRepo.findById(dirID);

        try {
            shareInfoOpt.orElseThrow(() -> new Exception("Directory has expired or might have never existed"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String downloadUrl = FileSystem.concatDirs(dirID);
        ShareInfo shareInfo = this.updateShareInfo(shareInfoOpt, request);
        List<FileInfo> files = FileSystem.getFilesFromDir(dirID);

        return new Share(downloadUrl, shareInfo, files);
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