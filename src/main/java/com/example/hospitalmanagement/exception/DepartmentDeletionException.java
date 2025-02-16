package com.example.hospitalmanagement.exception;

/**
 * This class represents a custom exception that is thrown when an error occurs during the deletion of a department.
 * It extends the `RuntimeException` class, making it an unchecked exception.
 */
public class DepartmentDeletionException extends RuntimeException {

    /**
     * Constructor for creating a new `DepartmentDeletionException` with a custom error message.
     *
     * @param message The error message that describes the reason for the exception.
     */
    public DepartmentDeletionException(String message) {
        super(message); // Calls the constructor of the parent class (RuntimeException) with the provided message.
    }
}