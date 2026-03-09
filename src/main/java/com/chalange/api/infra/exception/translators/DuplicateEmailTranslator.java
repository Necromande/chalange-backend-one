package com.chalange.api.infra.exception.translators;

import com.chalange.api.infra.exception.ExceptionTranslator;
import com.chalange.api.infra.exception.TranslatedError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Detecta mensagens/causas típicas de violação de unique constraint de email
 * e traduz para 409 Conflict com mensagem amigável.
 */
@Component
public class DuplicateEmailTranslator implements ExceptionTranslator {

    @Override
    public TranslatedError translate(Throwable t) {
        String root = getRootMessage(t).toLowerCase();

        if (root.contains("duplicate") ||
                root.contains("duplicate entry") ||
                root.contains("unique") ||
                root.contains("uq_usuario_email") ||
                root.contains("duplicate key") ||
                root.contains("constraint") && root.contains("email")) {

            return new TranslatedError(HttpStatus.CONFLICT, "Email já cadastrado.");
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