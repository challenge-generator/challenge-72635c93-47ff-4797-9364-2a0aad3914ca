package com.fintech.loans.infrastructure.config;

import com.fintech.loans.domain.port.CreditBureauService.CreditBureauException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor no válido",
                        (existing, replacement) -> existing
                ));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validación de datos fallida")
                .message("Los datos proporcionados no cumplen con las validaciones requeridas")
                .fieldErrors(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CreditBureauException.class)
    public ResponseEntity<ErrorResponse> handleCreditBureauException(
            CreditBureauException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Servicio de crédito no disponible")
                .message("No se pudo completar la verificación con el buró de crédito: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Parámetros inválidos")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Estado inconsistente")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error interno del servidor")
                .message("Ocurrió un error inesperado. Por favor, contacte al administrador.")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> fieldErrors;
        private String path;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public Instant getTimestamp() { return timestamp; }
        public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Map<String, String> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
    }

    public static class ErrorResponseBuilder {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> fieldErrors = new HashMap<>();
        private String path;

        public ErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
            this.fieldErrors = fieldErrors;
            return this;
        }

        public ErrorResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse response = new ErrorResponse();
            response.setTimestamp(timestamp);
            response.setStatus(status);
            response.setError(error);
            response.setMessage(message);
            response.setFieldErrors(fieldErrors);
            response.setPath(path);
            return response;
        }
    }
}