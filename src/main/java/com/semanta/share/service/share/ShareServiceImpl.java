package com.semanta.share.service.share;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semanta.share.utils.FileInfo;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.repository.ShareInfoRepository;
import com.semanta.share.utils.DelDirTask;
import com.semanta.share.utils.LookupIP;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    private final String SHARE_DIR = "tmp-dirs";
    private final String WRk_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

    @Override
    public String upload(long timeout, HttpServletRequest request) {
        String dirID = this.makeTmpDir();

        String country = LookupIP.lookup(request.getRemoteAddr());
        this.saveShareInfo(dirID, timeout, country);
        new DelDirTask(timeout, concatDirs(dirID));
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