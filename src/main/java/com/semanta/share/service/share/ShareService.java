package com.semanta.share.service.share;

import com.semanta.share.model.FileInfo;
import com.semanta.share.model.ShareInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ShareService {
    public abstract String upload(String delOnFirstView, long timeout, HttpServletRequest request);

    public abstract List<FileInfo> retrieve(String dirID);

    public abstract List<ShareInfo> retrieveAll();
}