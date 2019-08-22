package com.semanta.share.service.share;

import com.semanta.share.utils.Share;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.semanta.share.model.ShareInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ShareService {
    public abstract String upload(MultipartFile[] files, long timeout, HttpServletRequest request);

    public abstract Share retrieve(String dirID, HttpServletRequest request);

    public abstract ResponseEntity<Resource> download(String fileName);

    public abstract List<ShareInfo> retrieveAll();
}