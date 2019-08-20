package com.semanta.share.controller;

import com.semanta.share.model.FileInfo;
import com.semanta.share.model.ShareInfo;
import com.semanta.share.service.share.ShareServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.semanta.share.utils.LookupIP;

@RestController
@RequestMapping("share")
public class ShareController {

    @Autowired
    private ShareServiceImpl shareService;

    @GetMapping("/health")
    public String checkHealth() {
        return "OK";
    }

    @GetMapping("/ip_test")
    public ArrayList<String> ipTest() {
        return LookupIP.lookup("8.8.8.8");
    }

    @GetMapping("/retrieve_info_debugg")
    public List<ShareInfo> retrieveAll() {
        return shareService.retrieveAll();
    }

    @GetMapping("/retrieve")
    public List<FileInfo> retrieve(@RequestParam String dirID) {
        return shareService.retrieve(dirID);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String delOnFirstView, int timeout) {
        return shareService.upload(delOnFirstView, timeout);
    }
}
