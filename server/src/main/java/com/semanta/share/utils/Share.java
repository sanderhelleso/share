package com.semanta.share.utils;

import java.util.List;

import com.semanta.share.model.ShareInfo;

public class Share {
    private String downloadUrl;
    private long totalSize;
    private ShareInfo info;
    private List<FileInfo> files;

    public Share(String downloadUrl, ShareInfo info, List<FileInfo> files) {
        this.downloadUrl = FileSystem.getUri(downloadUrl);
        this.totalSize = calcSize(files);
        this.info = info;
        this.files = files;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public Long getTotalSize() {
        return this.totalSize;
    }

    public ShareInfo getInfo() {
        return this.info;
    }

    public List<FileInfo> getFiles() {
        return this.files;
    }

    private Long calcSize(List<FileInfo> files) {
        long sum = 0;
        for (FileInfo fileInfo : files) {
            sum += fileInfo.getSize();
        }

        return sum;
    }
}