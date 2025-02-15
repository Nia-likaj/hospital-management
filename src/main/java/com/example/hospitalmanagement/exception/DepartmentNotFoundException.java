package com.example.hospitalmanagement.exception;

/**
 * This class represents a custom exception that is thrown when a department is not found in the system.
 * It extends the `RuntimeException` class, making it an unchecked exception.
 */
public class DepartmentNotFoundException extends RuntimeException {

    /**
     * Constructor for creating a new `DepartmentNotFoundException` with a custom error message.
     *
     * @param message The error message that describes the reason for the exception.
     */
    public DepartmentNotFoundException(String message) {
        super(message); // Calls the constructor of the parent class (RuntimeException) with the provided message.
    }
}