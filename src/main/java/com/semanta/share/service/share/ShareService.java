package com.semanta.share.service.share;

import com.semanta.share.model.FileInfo;
import com.semanta.share.model.ShareInfo;

import java.util.List;

public interface ShareService {
    public abstract String upload(String delOnFirstView, long timeout);

    public abstract List<FileInfo> retrieve(String dirID);

    public abstract List<ShareInfo> retrieveAll();
}