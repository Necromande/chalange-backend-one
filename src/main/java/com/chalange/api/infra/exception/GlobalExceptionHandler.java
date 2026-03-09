package com.chalange.api.infra.exception;

import com.chalange.api.domain.exception.DuplicateEmailException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.PersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            DataIntegrityViolationException.class,
            ConstraintViolationException.class,
            SQLIntegrityConstraintViolationException.class,
            PersistenceException.class,
            TransactionSystemException.class
    })
    public ResponseEntity<Map<String, String>> handleConstraintExceptions(Exception ex) {
        Throwable root = getRootCause(ex);
        String rootMsg = root == null ? "" : root.getMessage().toLowerCase();

        String mensagem = "Violação de integridade de dados.";

        if (rootMsg.contains("duplicate") || rootMsg.contains("duplicate entry")
                || rootMsg.contains("unique") || rootMsg.contains("uq_usuario_email")) {
            mensagem = "Email já cadastrado.";
            logger.warn("Constraint violation detected (duplicate): {}", rootMsg);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", mensagem));
        }

        if (rootMsg.contains("null") || rootMsg.contains("cannot be null") || rootMsg.contains("not null")) {
            mensagem = "Campo obrigatório ausente.";
            logger.warn("Constraint violation detected (not null): {}", rootMsg);
            return ResponseEntity.badRequest().body(Map.of("erro", mensagem));
        }

        logger.error("Constraint error (root): {}", rootMsg, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", "Violação de integridade de dados."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        String firstError = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getDefaultMessage())
                .orElse("Requisição inválida.");
        logger.warn("Validation failed: {}", firstError);
        return ResponseEntity.badRequest().body(Map.of("erro", firstError));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Illegal argument: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        logger.error("Erro inesperado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erro", "Erro interno. Tente novamente mais tarde."));
    }

    private Throwable getRootCause(Throwable t) {
        Throwable root = t;
        while (root != null && root.getCause() != null && !Objects.equals(root, root.getCause())) {
            root = root.getCause();
        }
        return root;
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmail(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("erro", ex.getMessage()));
    }
}