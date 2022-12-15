package com.nhnacademy.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedAcceptHeader extends RuntimeException {
    public NotAllowedAcceptHeader(String message) {
        super(message);
    }
}
