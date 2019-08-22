package com.semanta.share.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDir {

    public static String zip(String dirID) throws IOException {
        String zipPath = "src/main/resources/tmp-zips/" + dirID + ".zip";

        // skip if zip exists
        if (new File(zipPath).exists()) {
            return zipPath;
        }

        FileOutputStream fos = new FileOutputStream(zipPath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(FileSystem.concatDirs(dirID));

        zipFile(fileToZip, dirID, zipOut);
        zipOut.close();
        fos.close();

        return zipPath;
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }

        if (fileToZip.isDirectory()) {
            if (!fileName.endsWith(File.separator)) {
                fileName += File.separatorChar;
            }

            zipOut.putNextEntry(new ZipEntry(fileName));
            zipOut.closeEntry();

            File[] files = fileToZip.listFiles();
            for (File file : files) {
                zipFile(file, fileName + file.getName(), zipOut);
            }

            return;
        }

        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int len = 0;

        while ((len = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, len);
        }

        fis.close();
    }
}