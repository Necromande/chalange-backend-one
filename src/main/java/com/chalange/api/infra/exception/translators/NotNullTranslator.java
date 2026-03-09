package com.chalange.api.infra.exception.translators;

import com.chalange.api.infra.exception.ExceptionTranslator;
import com.chalange.api.infra.exception.TranslatedError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Tradução simples para erros que indicam campos nulos/NOT NULL violations
 * ou mensagens de validação que mencionam "null" / "not null".
 */
@Component
public class NotNullTranslator implements ExceptionTranslator {

    @Override
    public TranslatedError translate(Throwable t) {
        String root = getRootMessage(t).toLowerCase();

        if (root.contains("not null") ||
                root.contains("cannot be null") ||
                root.contains("null value") ||
                root.contains("is null") ||
                root.contains("constraint") && root.contains("not null")) {

            return new TranslatedError(HttpStatus.BAD_REQUEST, "Campo obrigatório ausente ou nulo.");
        }

        return null;
    }

    private String getRootMessage(Throwable t) {
        Throwable root = t;
        while (root != null && root.getCause() != null && root != root.getCause()) {
            root = root.getCause();
        }
        return root == null || root.getMessage() == null ? "" : root.getMessage();
    }
}