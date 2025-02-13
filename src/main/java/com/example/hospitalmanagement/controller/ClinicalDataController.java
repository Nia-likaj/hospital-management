package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.ClinicalDataDTO;
import com.example.hospitalmanagement.entity.ClinicalData;
import com.example.hospitalmanagement.service.ClinicalDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinical-data")
public class ClinicalDataController {

    private final ClinicalDataService clinicalDataService;

    public ClinicalDataController(ClinicalDataService clinicalDataService) {
        this.clinicalDataService = clinicalDataService;
    }

    @PostMapping
    public ResponseEntity<ClinicalData> addClinicalData(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        return ResponseEntity.ok(clinicalDataService.addClinicalData(clinicalDataDTO));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<ClinicalDataDTO>> getClinicalRecordsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(clinicalDataService.getClinicalRecordsByPatient(patientId));
    }
}
