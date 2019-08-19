package com.semanta.share.service.share;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {

    public String makeTmpDir() {
        String dirName = this.genNounce();
        new File("/" + dirName).mkdirs();
        return dirName;
    }

    private String genNounce() {
        return UUID.randomUUID().toString();
    }
}