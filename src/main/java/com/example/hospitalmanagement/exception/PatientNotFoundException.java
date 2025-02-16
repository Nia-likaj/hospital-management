package com.example.hospitalmanagement.exception;

/**
 * This class represents a custom exception that is thrown when a patient is not found in the system.
 * It extends the `RuntimeException` class, making it an unchecked exception.
 */
public class PatientNotFoundException extends RuntimeException {

    /**
     * Constructor for creating a new `PatientNotFoundException` with a custom error message.
     *
     * @param message The error message that describes the reason for the exception.
     */
    public PatientNotFoundException(String message) {
        super(message); // Calls the constructor of the parent class (RuntimeException) with the provided message.
    }
}