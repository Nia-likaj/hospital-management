package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.dto.ClinicalDataDTO;
import com.example.hospitalmanagement.entity.ClinicalData;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.ClinicalDataRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicalDataService {

    private final ClinicalDataRepository clinicalDataRepository;
    private final PatientRepository patientRepository;

    public ClinicalDataService(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }

    public ClinicalData addClinicalData(ClinicalDataDTO clinicalDataDTO) {
        Patient patient = patientRepository.findById(clinicalDataDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        ClinicalData clinicalData = new ClinicalData(patient, clinicalDataDTO.getClinicalRecord());
        return clinicalDataRepository.save(clinicalData);
    }

    public List<ClinicalDataDTO> getClinicalRecordsByPatient(Long patientId) {
        return clinicalDataRepository.findByPatientId(patientId).stream()
                .map(record -> new ClinicalDataDTO(record.getPatient().getId(), record.getClinicalRecord()))
                .collect(Collectors.toList());
    }
}
