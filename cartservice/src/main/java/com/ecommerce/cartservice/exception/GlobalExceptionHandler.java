package com.ecommerce.cartservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        // Log the exception (you can use a logging framework like SLF4J)
        return ResponseEntity.badRequest().body(ex.getMessage()); // Return the exception message as the response body
    }
}