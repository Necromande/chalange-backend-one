package com.chalange.api.domain.exception;

import com.chalange.api.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends DomainException {
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message); // Enviando 404
    }
}