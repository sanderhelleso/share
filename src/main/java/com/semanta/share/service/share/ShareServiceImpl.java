package com.semanta.share.service.share;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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
        String dirNonce = this.makeTmpDir();

        Date now = new Date();

        if (delOnFirstView == "1") {
            timeout = MAX_AGE;
        }

        ShareInfo shareInfo = new ShareInfo(0, now, new Date(now.getTime() + timeout), delOnFirstView == "1", "Norway");
        shareInfoRepository.save(shareInfo);

        new DelDirTask(timeout, concatDirs(dirNonce));
        return dirNonce;
    }

    @Override
    public ArrayList<FileInfo> retrieve(String dirNonce) {
        File dir = new File(concatDirs(dirNonce));
        if (!dir.isDirectory()) {
            // throw new Exception("Directory has expired or might have never existed");
        }

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

    private String makeTmpDir() {
        String nonce = this.genNonce();
        String dirName = concatDirs(nonce);

        new File(dirName).mkdirs();
        return nonce;
    }

    private String genNonce() {
        return UUID.randomUUID().toString();
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