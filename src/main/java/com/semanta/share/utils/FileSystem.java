package com.semanta.share.utils;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Paths;
import java.io.File;
import java.net.URLConnection;

public class FileSystem {
    private static final String SHARE_DIR = "tmp-dirs";
    private static final String WRk_DIR = Paths.get(".").toAbsolutePath().normalize().toString();

    public static void uploadFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            String fileName = concatDirs(file.getOriginalFilename());
            ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString();
        }
    }

    public static String makeTmpDir() {
        String hash = UUID.randomUUID().toString();
        String dirName = concatDirs(hash);

        new File(dirName).mkdirs();
        return hash;
    }

    public static String getMimeType(File file) {
        if (file.isDirectory()) {
            return "text/directory";
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        // fallback to the default content type if type could not be determined
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    public static long getSize(File dir) {
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

    public static String concatDirs(String s) {
        return WRk_DIR + File.separatorChar + SHARE_DIR + File.separatorChar + s;
    }
}