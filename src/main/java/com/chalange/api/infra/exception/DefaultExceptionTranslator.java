package com.chalange.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Agregador de translators. Itera sobre os translators registrados e
 * retorna o primeiro TranslatedError não nulo. Se nenhum traduzir,
 * retorna um erro genérico.
 */
@Component
public class DefaultExceptionTranslator implements ExceptionTranslator {

    private final List<ExceptionTranslator> translators;

    public DefaultExceptionTranslator(List<ExceptionTranslator> translators) {
        this.translators = translators;
    }

    @Override
    public TranslatedError translate(Throwable t) {
        if (t == null) {
            return new TranslatedError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno.");
        }

        for (ExceptionTranslator tr : translators) {
            // evita chamar a si mesmo caso esteja na lista
            if (Objects.equals(tr, this)) continue;
            try {
                TranslatedError te = tr.translate(t);
                if (te != null) return te;
            } catch (Exception ignored) {
                // não deixar que um translator quebre a cadeia
            }
        }

        // fallback genérico
        return new TranslatedError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno. Tente novamente mais tarde.");
    }
}