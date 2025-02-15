package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.AdmissionDTO;
import com.example.hospitalmanagement.entity.AdmissionState;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.AdmissionRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a service layer component that handles business logic related to patient admissions.
 * It interacts with the `AdmissionRepository` and `PatientRepository` to perform operations on admission records.
 */
@Service // Marks this class as a Spring service component, making it a managed bean in the Spring context.
public class AdmissionService {

    private final AdmissionRepository admissionRepository; // Repository for admission-related operations.
    private final PatientRepository patientRepository; // Repository for patient-related operations.

    /**
     * Constructor for dependency injection.
     *
     * @param admissionRepository The repository for admission-related operations.
     * @param patientRepository The repository for patient-related operations.
     */
    public AdmissionService(AdmissionRepository admissionRepository, PatientRepository patientRepository) {
        this.admissionRepository = admissionRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Admits a patient to the hospital by creating a new admission record.
     *
     * @param admissionDTO A data transfer object containing the details of the admission.
     * @return The saved `AdmissionState` entity.
     * @throws RuntimeException If the patient ID is null or the patient is not found.
     */
    public AdmissionState admitPatient(AdmissionDTO admissionDTO) {
        Long patientId = admissionDTO.getPatientId();
        if (patientId == null) {
            throw new RuntimeException("Patient ID cannot be null"); // Validate that the patient ID is not null.
        }

        // Fetch the patient from the database or throw an exception if not found.
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Create a new admission record with the current timestamp.
        AdmissionState admission = new AdmissionState(patient, admissionDTO.getCause(), LocalDateTime.now());

        // Save the admission record to the database and return it.
        return admissionRepository.save(admission);
    }

    /**
     * Retrieves all admission records for a specific patient by their ID.
     *
     * @param patientId The ID of the patient whose admission records are to be retrieved.
     * @return A list of `AdmissionDTO` objects representing the patient's admission records.
     */
    public List<AdmissionDTO> getAdmissionsByPatient(Long patientId) {
        // Fetch all admission records for the specified patient.
        List<AdmissionState> admissions = admissionRepository.findByPatientId(patientId);

        // Convert the list of `AdmissionState` entities to a list of `AdmissionDTO` objects.
        return admissions.stream()
                .map(admission -> new AdmissionDTO(admission.getPatient().getId(), admission.getCause()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all admission records in the system.
     *
     * @return A list of `AdmissionDTO` objects representing all admission records.
     */
    public List<AdmissionDTO> getAllAdmissions() {
        // Fetch all admission records from the database.
        List<AdmissionState> admissions = admissionRepository.findAll();

        // Convert the list of `AdmissionState` entities to a list of `AdmissionDTO` objects.
        return admissions.stream()
                .map(admission -> new AdmissionDTO(admission.getPatient().getId(), admission.getCause()))
                .collect(Collectors.toList());
    }
}