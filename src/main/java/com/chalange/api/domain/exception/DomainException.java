package com.chalange.api.domain.exception;

/**
 * Exceção base para erros de domínio.
 * Mantém a hierarquia para facilitar captura e tradução no handler.
 */
public abstract class DomainException extends RuntimeException {
    public DomainException() { super(); }
    public DomainException(String message) { super(message); }
    public DomainException(String message, Throwable cause) { super(message, cause); }
    public DomainException(Throwable cause) { super(cause); }
}