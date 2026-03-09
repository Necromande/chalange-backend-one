package com.chalange.api.infra.exception;

/**
 * Interface para traduzir exceções em TranslatedError.
 * Implementações retornam null quando não souberem traduzir.
 */
public interface ExceptionTranslator {
    TranslatedError translate(Throwable t);
}