package com.example.cadastro.exceptions;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Exceção de validação de dados de entrada
     * @param ex
     * @return Mensagem de erro detalhada
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String messageError = error.getDefaultMessage();
            errors.put(fieldName, messageError);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionDetails(
                "Bad Request! Consult the documentation",
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().toString(),
                errors
        ));
    }

    /**
     * Exceção para dados inexistentes
     * @param ex
     * @return Mensagem de erro detalhada
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDetails> handlerValidException(BusinessException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("null", ex.getMessage());

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                "NOT_FOUND! Consult the documentation",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().toString(),
                errorDetails
        );

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Exceção utilizada quando há conflito de dados
     * @param ex
     * @return Mensagem de erro detalhada
     */
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<ExceptionDetails> handleDataAccessException(DataAccessException ex) {
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(new ExceptionDetails(
//                "Conflict! Consult the documentation",
//                        LocalDateTime.now(),
//                        HttpStatus.CONFLICT.value(),
//                        ex.getClass().toString(),
//                        Map.of(ex.getCause().toString(), ex.getMessage())
//        ));
//    }

}