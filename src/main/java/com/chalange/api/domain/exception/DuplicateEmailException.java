package com.chalange.api.domain.exception;

/**
 * Lançada quando já existe um usuário com o mesmo email.
 */
public class DuplicateEmailException extends DomainException {
    public DuplicateEmailException() { super("Email já cadastrado."); }
    public DuplicateEmailException(String message) { super(message); }
    public DuplicateEmailException(String message, Throwable cause) { super(message, cause); }
}