package com.semanta.share.controller;

import com.semanta.share.model.FileInfo;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.service.share.ShareServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("share")
public class ShareController {

    @Autowired
    private ShareServiceImpl shareService;

    @GetMapping("/health")
    public String checkHealth() {
        return "OK";
    }

    @GetMapping("/retrieve")
    public List<FileInfo> retrieve(@RequestParam String dirNonce) {
        return shareService.retrieve(dirNonce);
    }

    @GetMapping("/retrieve_info_debugg")
    public List<ShareInfo> retrieveAll() {
        return shareService.retrieveAll();
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String delOnFirstView, int timeout) {
        return shareService.upload(delOnFirstView, timeout);
    }
}
