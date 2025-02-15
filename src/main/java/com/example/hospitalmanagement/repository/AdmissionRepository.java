package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is a Spring Data JPA repository for the `AdmissionState` entity.
 * It provides methods to perform database operations related to patient admissions.
 */
@Repository // Marks this interface as a Spring Data repository, making it a managed bean in the Spring context.
public interface AdmissionRepository extends JpaRepository<AdmissionState, Long> {

    /**
     * Finds all admission records for a specific patient by their ID.
     *
     * @param patientId The ID of the patient whose admission records are to be retrieved.
     * @return A list of `AdmissionState` objects associated with the specified patient.
     */
    List<AdmissionState> findByPatientId(Long patientId); // Custom query method to find admissions by patient ID.
}