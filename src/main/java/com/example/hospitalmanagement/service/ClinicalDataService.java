package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.ClinicalDataDTO;
import com.example.hospitalmanagement.entity.ClinicalData;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.ClinicalDataRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a service layer component that handles business logic related to clinical data.
 * It interacts with the `ClinicalDataRepository` and `PatientRepository` to perform operations on clinical records.
 */
@Service // Marks this class as a Spring service component, making it a managed bean in the Spring context.
public class ClinicalDataService {

    private final ClinicalDataRepository clinicalDataRepository; // Repository for clinical data-related operations.
    private final PatientRepository patientRepository; // Repository for patient-related operations.

    /**
     * Constructor for dependency injection.
     *
     * @param clinicalDataRepository The repository for clinical data-related operations.
     * @param patientRepository The repository for patient-related operations.
     */
    public ClinicalDataService(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Adds a new clinical record for a patient.
     *
     * @param clinicalDataDTO A data transfer object containing the details of the clinical record.
     * @return The saved `ClinicalData` entity.
     * @throws RuntimeException If the patient is not found.
     */
    public ClinicalData addClinicalData(ClinicalDataDTO clinicalDataDTO) {
        // Fetch the patient from the database or throw an exception if not found.
        Patient patient = patientRepository.findById(clinicalDataDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create a new clinical record entity.
        ClinicalData clinicalData = new ClinicalData(patient, clinicalDataDTO.getClinicalRecord());

        // Save the clinical record to the database and return it.
        return clinicalDataRepository.save(clinicalData);
    }

    /**
     * Retrieves all clinical records for a specific patient by their ID.
     *
     * @param patientId The ID of the patient whose clinical records are to be retrieved.
     * @return A list of `ClinicalDataDTO` objects representing the patient's clinical records.
     */
    public List<ClinicalDataDTO> getClinicalRecordsByPatient(Long patientId) {
        // Fetch all clinical records for the specified patient.
        return clinicalDataRepository.findByPatientId(patientId).stream()
                // Convert each `ClinicalData` entity to a `ClinicalDataDTO` object.
                .map(record -> new ClinicalDataDTO(record.getPatient().getId(), record.getClinicalRecord()))
                // Collect the results into a list.
                .collect(Collectors.toList());
    }
}