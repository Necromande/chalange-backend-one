package com.chalange.api.domain.exception;

/**
 * Exceção lançada quando um recurso não é encontrado (usuário, tópico, resposta, etc).
 * Estende DomainException para manter a hierarquia de exceções de domínio.
 */
public class ResourceNotFoundException extends DomainException {

    public ResourceNotFoundException() {
        super("Recurso não encontrado.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}