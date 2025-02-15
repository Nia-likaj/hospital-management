package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is a Spring Data JPA repository for the `ClinicalData` entity.
 * It provides methods to perform database operations related to clinical records or medical history of patients.
 */
@Repository // Marks this interface as a Spring Data repository, making it a managed bean in the Spring context.
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {

    /**
     * Finds all clinical records for a specific patient by their ID.
     *
     * @param patientId The ID of the patient whose clinical records are to be retrieved.
     * @return A list of `ClinicalData` objects associated with the specified patient.
     */
    List<ClinicalData> findByPatientId(Long patientId); // Custom query method to find clinical records by patient ID.
}