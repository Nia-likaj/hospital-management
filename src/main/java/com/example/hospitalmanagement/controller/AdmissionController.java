package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.AdmissionDTO;
import com.example.hospitalmanagement.entity.AdmissionState;
import com.example.hospitalmanagement.service.AdmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admissions")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping
    public ResponseEntity<AdmissionState> admitPatient(@RequestBody AdmissionDTO admissionDTO) {
        return ResponseEntity.ok(admissionService.admitPatient(admissionDTO));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<AdmissionDTO>> getAdmissionsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(admissionService.getAdmissionsByPatient(patientId));
    }
}
