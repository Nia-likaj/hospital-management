package com.example.hospitalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the Hospital Management Spring Boot application.
 * It is annotated with `@SpringBootApplication`, which enables auto-configuration, component scanning,
 * and other Spring Boot features.
 */
@SpringBootApplication // Marks this class as the main Spring Boot application.
public class HospitalManagementApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launches the Spring Boot application.
        SpringApplication.run(HospitalManagementApplication.class, args);
    }
}