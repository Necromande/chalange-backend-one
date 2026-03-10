package com.chalange.api.domain.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends DomainException {
    public DuplicateEmailException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}