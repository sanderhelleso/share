package com.semanta.share.service.share;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semanta.share.model.FileInfo;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.repository.ShareInfoRepository;
import com.semanta.share.utils.DelDirTask;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareInfoRepository shareInfoRepository;

    private final int MAX_AGE = 172800000; // 2 days
    private final String SHARE_DIR = "tmp-dirs";
    private final String WRk_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

    @Override
    public String upload(String delOnFirstView, long timeout) {
        String dirID = this.makeTmpDir();

        this.saveShareInfo(dirID, delOnFirstView == "1", timeout);
        new DelDirTask(timeout, concatDirs(dirID));
        return dirID;
    }

    @Override
    public List<FileInfo> retrieve(String dirID) {
        Optional<ShareInfo> shareInfoOpt = shareInfoRepository.findById(dirID);

        try {
            shareInfoOpt.orElseThrow(() -> new Exception("Directory has expired or might have never existed"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.updateShareInfo(shareInfoOpt);

        File dir = new File(concatDirs(dirID));
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();

        for (File file : dir.listFiles()) {
            String name = file.getName();
            String dlPath = file.getPath();
            String type = this.getMimeType(file);
            long size = this.getSize(file);

            files.add(new FileInfo(name, type, dlPath, size));
        }

        return files;
    }

    @Override
    public List<ShareInfo> retrieveAll() {
        return shareInfoRepository.findAll();
    }

    private void saveShareInfo(String dirID, Boolean delOnFirstView, Long timeout) {
        if (delOnFirstView) {
            timeout = Long.valueOf(MAX_AGE);
        }

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + timeout);

        ShareInfo shareInfo = new ShareInfo(dirID, expiresAt, delOnFirstView, "Norway");
        shareInfoRepository.save(shareInfo);
    }

    private void updateShareInfo(Optional<ShareInfo> shareInfoOpt) {
        ShareInfo shareInfo = shareInfoOpt.get();
        shareInfo.setLastDownloadedAt();
        shareInfo.setTotDownloads();
        shareInfoRepository.save(shareInfo);
    }

    private String makeTmpDir() {
        String hash = UUID.randomUUID().toString();
        String dirName = concatDirs(hash);

        new File(dirName).mkdirs();
        return hash;
    }

    private String getMimeType(File file) {
        if (file.isDirectory()) {
            return "folder";
        }

        return URLConnection.guessContentTypeFromName(file.getName());
    }

    private long getSize(File dir) {
        long sum = 0;

        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                sum += getSize(file);
            }
        } else {
            sum = dir.length();
        }

        return sum;
    }

    private String concatDirs(String s) {
        return WRk_DIR + File.separatorChar + SHARE_DIR + File.separatorChar + s;
    }
}