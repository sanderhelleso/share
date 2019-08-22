package com.semanta.share.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class StorageFullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StorageFullException(String message) {
        super(message);
    }
}