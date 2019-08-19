package com.semanta.share.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("share")
public class ShareController {

    @GetMapping("/health")
    public String checkHealth() {
        return "OK";
    }
}

