package com.semanta.share.controller;

import com.semanta.share.service.share.ShareServiceImpl;
import com.semanta.share.utils.DelDirTask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("share")
public class ShareController {

    private ShareServiceImpl shareService = new ShareServiceImpl();

    @GetMapping("/health")
    public String checkHealth() {
        return "OK";
    }

    @PostMapping("/upload")
    public String upload() {
        String dirName = shareService.makeTmpDir();
        new DelDirTask(5000, dirName);
        return dirName;
    }
}
