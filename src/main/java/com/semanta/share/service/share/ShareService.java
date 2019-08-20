package com.semanta.share.service.share;

import com.semanta.share.model.FileInfo;

import java.util.ArrayList;

public interface ShareService {
    public abstract String upload(String delOnFirstView, long timeout);

    public abstract ArrayList<FileInfo> retrieve(String dirNonce) throws Exception;
}