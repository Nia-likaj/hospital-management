package com.example.hospitalmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a global exception handler for the application.
 * It uses the `@RestControllerAdvice` annotation to handle exceptions across all controllers.
 * It provides centralized exception handling and returns consistent error responses to the client.
 */
@RestControllerAdvice // Indicates that this class provides global exception handling for all controllers.
public class GlobalExceptionHandler {

    /**
     * Handles `ResourceNotFoundException` and returns a custom error response.
     *
     * @param ex The exception that was thrown.
     * @return A `ResponseEntity` containing the error details and an HTTP status code of 404 (NOT FOUND).
     */
    @ExceptionHandler(ResourceNotFoundException.class) // Specifies that this method handles `ResourceNotFoundException`.
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Create a map to store error details.
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now()); // Add the current timestamp.
        errorDetails.put("message", ex.getMessage()); // Add the exception message.
        errorDetails.put("status", HttpStatus.NOT_FOUND.value()); // Add the HTTP status code.

        // Return the error details with an HTTP status of 404 (NOT FOUND).
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other exceptions and returns a custom error response.
     *
     * @param ex The exception that was thrown.
     * @return A `ResponseEntity` containing the error details and an HTTP status code of 500 (INTERNAL SERVER ERROR).
     */
    @ExceptionHandler(Exception.class) // Specifies that this method handles all other exceptions.
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        // Create a map to store error details.
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now()); // Add the current timestamp.
        errorDetails.put("message", ex.getMessage()); // Add the exception message.
        errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // Add the HTTP status code.

        // Return the error details with an HTTP status of 500 (INTERNAL SERVER ERROR).
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}