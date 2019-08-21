package com.semanta.share.controller;

import com.semanta.share.utils.FileInfo;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.service.share.ShareServiceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("share")
public class ShareController {

    @Autowired
    private ShareServiceImpl shareService;

    @GetMapping("/ip_test")
    public String ipTest(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping("/retrieve_info_debug")
    public List<ShareInfo> retrieveAll() {
        return shareService.retrieveAll();
    }

    @GetMapping("/retrieve")
    public List<FileInfo> retrieve(@RequestParam String dirID, HttpServletRequest request) {
        return shareService.retrieve(dirID, request);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] files, int timeout, HttpServletRequest request) {
        return shareService.upload(files, timeout, request);
    }
}
