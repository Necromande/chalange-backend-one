//package com.chalange.api.infra.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError; // Resolve getFieldErrors e getField
//import org.springframework.web.bind.MethodArgumentNotValidException; // Resolve a Exception
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    // 1. Tratamento de Erros de Validação (Bean Validation)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<List<DadosErroValidacao>> handleValidationError(MethodArgumentNotValidException ex) {
//        var erros = ex.getFieldErrors().stream()
//                .map(DadosErroValidacao::new)
//                .toList();
//        return ResponseEntity.badRequest().body(erros);
//    }
//
//    // 2. Tratamento das suas Exceções Customizadas (404, 409, etc)
//    @ExceptionHandler(HttpException.class)
//    public ResponseEntity<Map<String, String>> handleHttpException(HttpException ex) {
//        return ResponseEntity.status(ex.getStatus())
//                .body(Map.of("erro", ex.getMessage()));
//    }
//
//    // 3. Tratamento de Erros Genéricos (500)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Map.of("erro", "Erro interno no servidor."));
//    }
//
//    // Record auxiliar para formatar o JSON de erro de validação
//    private record DadosErroValidacao(String campo, String mensagem) {
//        public DadosErroValidacao(FieldError erro) {
//            this(erro.getField(), erro.getDefaultMessage());
//        }
//    }
//}