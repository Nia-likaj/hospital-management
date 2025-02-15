package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.PatientDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a service layer component that handles business logic related to patients.
 * It interacts with the `PatientRepository` and `DepartmentRepository` to perform operations on patient records.
 */
@Service // Marks this class as a Spring service component, making it a managed bean in the Spring context.
public class PatientService {

    private final PatientRepository patientRepository; // Repository for patient-related operations.
    private final DepartmentRepository departmentRepository; // Repository for department-related operations.

    /**
     * Constructor for dependency injection.
     *
     * @param patientRepository The repository for patient-related operations.
     * @param departmentRepository The repository for department-related operations.
     */
    public PatientService(PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * Saves a new patient to the database.
     *
     * @param patientDTO A data transfer object containing the details of the patient.
     * @return The saved `Patient` entity.
     * @throws RuntimeException If the department is not found.
     */
    public Patient savePatient(PatientDTO patientDTO) {
        // Fetch the department from the database or throw an exception if not found.
        Department department = departmentRepository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Create a new patient entity using the data from the DTO.
        Patient patient = new Patient(patientDTO.getName(), patientDTO.getLastName(), patientDTO.getBirthDate(), department);

        // Save the patient to the database and return it.
        return patientRepository.save(patient);
    }

    /**
     * Retrieves all patients from the database.
     *
     * @return A list of `PatientDTO` objects representing all patients.
     */
    public List<PatientDTO> getAllPatients() {
        // Fetch all patients from the database.
        return patientRepository.findAll().stream()
                // Convert each `Patient` entity to a `PatientDTO` object.
                .map(patient -> new PatientDTO(
                        patient.getName(),
                        patient.getLastName(),
                        patient.getBirthDate(),
                        patient.getDepartment().getId(),
                        null // Clinical record is not included in the list view.
                ))
                // Collect the results into a list.
                .collect(Collectors.toList());
    }
}