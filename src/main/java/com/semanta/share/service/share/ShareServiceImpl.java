package com.semanta.share.service.share;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {
    private final String SHARE_DIR = "tmp-dirs";
    private final String WRk_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

    public String makeTmpDir() {
        String dirName = concatDirs(this.genNounce());
        new File(dirName).mkdirs();
        return dirName;
    }

    private String genNounce() {
        return UUID.randomUUID().toString();
    }

    private String concatDirs(String s) {
        return WRk_DIR + File.separatorChar + SHARE_DIR + File.separatorChar + s;
    }
}