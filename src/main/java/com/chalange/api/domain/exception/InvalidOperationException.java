package com.chalange.api.domain.exception;

/**
 * Lançada quando uma operação solicitada não é permitida pelo estado atual da entidade.
 * Ex.: marcar resposta como solução quando já existe outra solução, etc.
 */
public class InvalidOperationException extends DomainException {
    public InvalidOperationException() { super("Operação inválida."); }
    public InvalidOperationException(String message) { super(message); }
    public InvalidOperationException(String message, Throwable cause) { super(message, cause); }
}