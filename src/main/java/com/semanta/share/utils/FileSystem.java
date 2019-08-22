package com.semanta.share.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

public class FileSystem {
    private static final String SHARE_DIR = "tmp-dirs";
    private static final String WRk_DIR = normalize(".").toString();

    public static void uploadFiles(MultipartFile[] files, String dirID) {
        for (MultipartFile file : files) {
            String fileName = dirID + File.separatorChar + file.getOriginalFilename();
            Path dest = normalize(concatDirs(fileName));
            try {
                Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public static List<FileInfo> getFilesFromDir(String dirID) {
        File dir = new File(concatDirs(dirID));
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();

        for (File file : dir.listFiles()) {
            String name = file.getName();
            String dlPath = getUri(file.getPath());
            String type = FileSystem.getMimeType(file);
            long size = FileSystem.getSize(file);

            files.add(new FileInfo(name, type, dlPath, size));
        }

        return files;
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

    public static String getUri(String s) {
        return normalize(s).toUri().toString();
    }

    private static Path normalize(String dest) {
        return Paths.get(dest).toAbsolutePath().normalize();
    }
}