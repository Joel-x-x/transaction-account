package com.bank.transactionaccount.infrastructure.config.exception;

import com.bank.transactionaccount.infrastructure.common.ResultResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GenericErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResultResponse<Object, String>> handleError404(EntityNotFoundException ex) {
        ResultResponse<Object, String> response = ResultResponse.failure(Collections.singletonList(ex.getMessage()), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse<Object, ValidationErrorData>> handleError400(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        List<ValidationErrorData> messages = new ArrayList<>();

        errors.forEach(error -> {
            messages.add(new ValidationErrorData(error.getField(), Collections.singletonList(error.getDefaultMessage())));
        });

        ResultResponse<Object, ValidationErrorData> response = ResultResponse.failure(messages, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultResponse<Object, String>> handleError400(HttpMessageNotReadableException ex) {
        ResultResponse<Object, String> response = ResultResponse.failure(Collections.singletonList(ex.getMessage()), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultResponse<Object, ValidationErrorData>> handleValidationError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<ValidationErrorData> messages = new ArrayList<>();

        // Collecting validation messages
        violations.forEach(violation -> {
            messages.add(new ValidationErrorData(violation.getPropertyPath().toString(), Collections.singletonList(violation.getMessage())));
        });

        // Assuming ResultResponse.failure() needs to return List<ValidationErrorData> as the first type argument and Integer as the second
        ResultResponse<Object, ValidationErrorData> response = ResultResponse.failure(messages, HttpStatus.BAD_REQUEST.value());

        // Returning the response as bad request
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultResponse<Object, String>> handleError500(Exception ex) {
        ResultResponse<Object, String> response = ResultResponse.failure(Collections.singletonList("Error interno del servidor: " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ResultResponse<Object, String>> handleError500(JpaSystemException ex) {
        ResultResponse<Object, String> response = ResultResponse.failure(Collections.singletonList("Error en el sistema: " + ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private record ValidationErrorData(String field, List<String> messages) {}
}