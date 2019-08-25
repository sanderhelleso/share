package com.semanta.share.utils;

public class FileInfo {
    private String name;
    private String type;
    private long size;

    public FileInfo(String name, String type, long size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public long getSize() {
        return this.size;
    }
}