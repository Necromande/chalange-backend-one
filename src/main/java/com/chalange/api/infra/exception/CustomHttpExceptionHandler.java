package com.chalange.api.infra.exception;

import com.chalange.api.infra.exception.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CustomHttpExceptionHandler {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Map<String, String>> handle(HttpException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(Map.of("erro", ex.getMessage()));
    }
}