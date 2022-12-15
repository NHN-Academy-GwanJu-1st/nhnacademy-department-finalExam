package com.nhnacademy.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundDepartment extends RuntimeException {
    public NotFoundDepartment(String message) {
        super(message);
    }
}
