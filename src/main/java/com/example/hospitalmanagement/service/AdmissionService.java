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

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;

    public AdmissionService(AdmissionRepository admissionRepository, PatientRepository patientRepository) {
        this.admissionRepository = admissionRepository;
        this.patientRepository = patientRepository;
    }

    public AdmissionState admitPatient(AdmissionDTO admissionDTO) {
        Long patientId = admissionDTO.getPatientId();
        if (patientId == null) {
            throw new RuntimeException("Patient ID cannot be null");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        AdmissionState admission = new AdmissionState(patient, admissionDTO.getCause(), LocalDateTime.now());
        return admissionRepository.save(admission);
    }

    public List<AdmissionDTO> getAdmissionsByPatient(Long patientId) {
        List<AdmissionState> admissions = admissionRepository.findByPatientId(patientId);
        return admissions.stream()
                .map(admission -> new AdmissionDTO(admission.getPatient().getId(), admission.getCause()))
                .collect(Collectors.toList());
    }
}