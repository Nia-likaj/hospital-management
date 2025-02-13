package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.PatientDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;

    public PatientService(PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }

    // Save a new patient
    public Patient savePatient(PatientDTO patientDTO) {
        Department department = departmentRepository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Patient patient = new Patient(patientDTO.getName(), patientDTO.getLastName(), patientDTO.getBirthDate(), department);
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> {
                    return new PatientDTO(
                            patient.getName(),
                            patient.getLastName(),
                            patient.getBirthDate(),
                            patient.getDepartment().getId(),
                            null);
                }) // No clinical record in list view
                .collect(Collectors.toList());
    }
}
