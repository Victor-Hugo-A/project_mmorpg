package com.mmorpg.project_pt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", "DADOS_INVÁLIDOS");
        response.put("timestamp", LocalDateTime.now().toString());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        response.put("message", "Dados de entrada inválidos");
        response.put("details", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("timestamp", LocalDateTime.now().toString());

        // Definir código de erro e status
        HttpStatus status;
        String errorCode;

        if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
            status = HttpStatus.NOT_FOUND;
            errorCode = "RECURSO_NAO_ENCONTRADO";
            response.put("message", e.getMessage());
            response.put("details", List.of("O recurso solicitado não foi encontrado."));
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errorCode = "ERRO_INTERNO";
            response.put("message", "Ocorreu um erro inesperado.");
            response.put("details", List.of(e.getMessage()));
        }

        response.put("error", errorCode);

        return ResponseEntity.status(status).body(response);
    }
}