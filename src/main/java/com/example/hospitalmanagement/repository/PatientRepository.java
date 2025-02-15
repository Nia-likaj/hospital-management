package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is a Spring Data JPA repository for the `Patient` entity.
 * It provides methods to perform database operations related to patients.
 */
@Repository // Marks this interface as a Spring Data repository, making it a managed bean in the Spring context.
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Finds all patients whose names contain the specified string, ignoring case.
     *
     * @param name The string to search for within patient names.
     * @return A list of `Patient` objects whose names contain the specified string (case-insensitive).
     */
    List<Patient> findByNameContainingIgnoreCase(String name); // Custom query method to search for patients by name.
}