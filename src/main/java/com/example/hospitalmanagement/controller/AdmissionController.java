package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.AdmissionDTO;
import com.example.hospitalmanagement.entity.AdmissionState;
import com.example.hospitalmanagement.service.AdmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring REST controller responsible for handling HTTP requests related to patient admissions.
 * It exposes endpoints for admitting patients and retrieving all admissions.
 */
@RestController // Indicates that this class is a REST controller, meaning it handles HTTP requests and returns JSON/XML responses.
@RequestMapping("/admissions") // Maps all endpoints in this controller to the base URL path "/admissions".
public class AdmissionController {

    // AdmissionService is a dependency that handles the business logic for admissions.
    private final AdmissionService admissionService;

    // Constructor-based dependency injection is used to inject the AdmissionService bean.
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    /**
     * Endpoint to admit a patient.
     * This method handles HTTP POST requests to the "/admissions" URL.
     *
     * @param admissionDTO A data transfer object (DTO) containing the details of the patient admission.
     * @return ResponseEntity<AdmissionState> Returns the admission state (e.g., success/failure) with an HTTP 200 OK status.
     */
    @PostMapping // Maps HTTP POST requests to this method.
    public ResponseEntity<AdmissionState> admitPatient(@RequestBody AdmissionDTO admissionDTO) {
        // Calls the admitPatient method in the AdmissionService to process the admission.
        // Returns the result wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(admissionService.admitPatient(admissionDTO));
    }

    /**
     * Endpoint to retrieve all admissions.
     * This method handles HTTP GET requests to the "/admissions" URL.
     *
     * @return ResponseEntity<List<AdmissionDTO>> Returns a list of all admissions with an HTTP 200 OK status.
     */
    @GetMapping // Maps HTTP GET requests to this method.
    public ResponseEntity<List<AdmissionDTO>> getAllAdmissions() {
        // Calls the getAllAdmissions method in the AdmissionService to fetch all admissions.
        // Returns the list of admissions wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(admissionService.getAllAdmissions());
    }
}