package com.chalange.api.infra.exception;

import org.springframework.http.HttpStatus;

public record TranslatedError(HttpStatus status, String message) {}