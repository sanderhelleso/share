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
    private static final Long MAX_STORAGE_SIZE = 107374182400L; // 100gb
    private static final String WRK_DIR = "src/main/resources/";
    private static final String SHARE_DIR = "tmp-dirs/"; // uploaded folders
    private static final String ZIP_DIR = "tmp-zips/"; // generated zip files

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

    public static Boolean canUpload(MultipartFile[] files) {
        long uploadSize = 0;
        for (MultipartFile file : files) {
            uploadSize += file.getSize();
        }

        long newTotSize = getSize(new File(WRK_DIR + SHARE_DIR)) + uploadSize;

        return newTotSize < MAX_STORAGE_SIZE;
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
        return WRK_DIR + SHARE_DIR + s;
    }

    public static String concatZip(String s) {
        return WRK_DIR + ZIP_DIR + s + ".zip";
    }

    public static String getUri(String s) {
        return normalize(s).toUri().toString();
    }

    private static Path normalize(String dest) {
        return Paths.get(dest).toAbsolutePath().normalize();
    }
}