package com.semanta.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DirNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DirNotFoundException(String message) {
        super(message);
    }
}