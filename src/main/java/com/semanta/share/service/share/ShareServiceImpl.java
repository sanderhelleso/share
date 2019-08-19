package com.semanta.share.service.share;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.semanta.share.utils.DelDirTask;

@Service
public class ShareServiceImpl implements ShareService {
    private final int MAX_AGE = 172800000; // 2 days
    private final String SHARE_DIR = "tmp-dirs";
    private final String WRk_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

    public String upload(String delOnFirstView, int timeout) {
        String dirNonce = this.makeTmpDir();

        if (delOnFirstView == "1") {
            timeout = MAX_AGE;
        }

        new DelDirTask(timeout, concatDirs(dirNonce));
        return dirNonce;
    }

    public ArrayList<String> retrieve(String dirNonce) {
        File dir = new File(concatDirs(dirNonce));
        if (!dir.isDirectory()) {
            // throw new Exception("Directory has expired or might have never existed");
        }

        ArrayList<String> files = new ArrayList<String>();
        for (File file : dir.listFiles()) {
            files.add(file.getName());
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

    private String concatDirs(String s) {
        return WRk_DIR + File.separatorChar + SHARE_DIR + File.separatorChar + s;
    }
}