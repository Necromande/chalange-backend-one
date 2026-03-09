package com.chalange.api.domain.exception;

/**
 * Exceção genérica para regras de negócio que não se encaixam em tipos mais específicos.
 */
public class BusinessException extends DomainException {
    public BusinessException() { super(); }
    public BusinessException(String message) { super(message); }
    public BusinessException(String message, Throwable cause) { super(message, cause); }
}