package com.learning.taskapi.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex){

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error -> 
              errors.put(
                error.getField(),
                error.getDefaultMessage()
              )  
            );

        ex.getBindingResult()
            .getGlobalErrors()
            .forEach(error ->
                errors.put(
                    "request",
                    error.getDefaultMessage()
                )
            );

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("message", "Error de validación");
        response.put("errors", errors);
        
        return ResponseEntity
            .badRequest()
            .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidJson(HttpMessageNotReadableException ex) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put(
            "message",
            "El cuerpo de la petición contiene JSON inválido"
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(TaskNotFoundException ex){

      Map<String, Object> response = new LinkedHashMap<>();

      response.put("timestamp", LocalDateTime.now());
      response.put("status", HttpStatus.NOT_FOUND.value());
      response.put("error", "Not Found");
      response.put("message" ,ex.getMessage());

      return ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(response);

    }
}
