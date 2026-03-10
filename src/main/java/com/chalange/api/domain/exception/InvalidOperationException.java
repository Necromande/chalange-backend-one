package com.chalange.api.domain.exception;

import com.chalange.api.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public class InvalidOperationException extends DomainException {
    public InvalidOperationException(String message) {
        super(HttpStatus.BAD_REQUEST, message); // Agora o Handler vai capturar!
    }
}