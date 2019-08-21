package com.semanta.share.service.share;

import com.semanta.share.utils.FileInfo;

import org.springframework.web.multipart.MultipartFile;

import com.semanta.share.model.ShareInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ShareService {
    public abstract String upload(MultipartFile[] files, long timeout, HttpServletRequest request);

    public abstract List<FileInfo> retrieve(String dirID, HttpServletRequest request);

    public abstract List<ShareInfo> retrieveAll();
}