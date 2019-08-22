package com.semanta.share.utils;

public class FileInfo {
    private String name;
    private String type;
    private String dlPath;
    private long size;

    public FileInfo(String name, String type, String dlPath, long size) {
        this.name = name;
        this.type = type;
        this.dlPath = dlPath;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getDlPath() {
        return this.dlPath;
    }

    public long getSize() {
        return this.size;
    }
}