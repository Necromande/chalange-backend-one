package com.chalange.api.domain.exception;

import com.chalange.api.infra.exception.HttpException;
import org.springframework.http.HttpStatus;

public abstract class DomainException extends HttpException { // Agora estende HttpException

    public DomainException(String message) {
        super(HttpStatus.BAD_REQUEST, message); // Status padrão para erros de domínio
    }

    public DomainException(HttpStatus status, String message) {
        super(status, message); // Permite que filhas escolham o status (ex: 404, 409)
    }
}