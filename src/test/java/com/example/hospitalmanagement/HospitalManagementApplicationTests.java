package com.example.hospitalmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This is the main test class for the Hospital Management Spring Boot application.
 * It is annotated with `@SpringBootTest`, which ensures that the application context is loaded
 * and all Spring-managed beans are available for testing.
 */
@SpringBootTest // Marks this class as a Spring Boot test, loading the full application context
class HospitalManagementApplicationTests {

    /**
     * This test method checks if the application context loads successfully.
     * If the context loads without errors, the test passes.
     */
    @Test
    void contextLoads() {
        // This test does not require any assertions because it simply checks if the application context loads.
        // If the context fails to load, the test will fail automatically.
    }
}