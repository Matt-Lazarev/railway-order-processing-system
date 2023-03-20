package com.lazarev.personalaccountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchClientOrderException extends RuntimeException {
    public NoSuchClientOrderException(String message) {
        super(message);
    }
}
