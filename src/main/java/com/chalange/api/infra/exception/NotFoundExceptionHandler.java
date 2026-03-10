package com.chalange.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}