package com.chalange.api.infra.exception;

import org.springframework.http.HttpStatus;

public class CampoObrigatorioException extends HttpException {
    public CampoObrigatorioException(String campo) {
        super(HttpStatus.BAD_REQUEST, "Campo obrigatório ausente: " + campo);
    }
}
