package com.chalange.api.domain.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends DomainException {
    public BusinessException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message); // Retorna 422
    }
}