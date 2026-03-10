package com.chalange.api.infra.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends HttpException {
    public DuplicateEmailException() {
        super(HttpStatus.CONFLICT, "Email já cadastrado.");
    }
}

