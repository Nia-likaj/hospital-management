package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.dto.ClinicalDataDTO;
import com.example.hospitalmanagement.entity.ClinicalData;
import com.example.hospitalmanagement.service.ClinicalDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a Spring REST controller responsible for handling HTTP requests related to clinical data.
 * It exposes endpoints for adding clinical data, retrieving clinical records for a specific patient, and a welcome message.
 */
@RestController // Indicates that this class is a REST controller, meaning it handles HTTP requests and returns JSON/XML responses.
@RequestMapping("/clinical-data") // Maps all endpoints in this controller to the base URL path "/clinical-data".
public class ClinicalDataController {

    // ClinicalDataService is a dependency that handles the business logic for clinical data.
    private final ClinicalDataService clinicalDataService;

    // Constructor-based dependency injection is used to inject the ClinicalDataService bean.
    public ClinicalDataController(ClinicalDataService clinicalDataService) {
        this.clinicalDataService = clinicalDataService;
    }

    /**
     * Endpoint to add clinical data.
     * This method handles HTTP POST requests to the "/clinical-data" URL.
     *
     * @param clinicalDataDTO A data transfer object (DTO) containing the details of the clinical data.
     * @return ResponseEntity<ClinicalData> Returns the added clinical data entity with an HTTP 200 OK status.
     */
    @PostMapping // Maps HTTP POST requests to this method.
    public ResponseEntity<ClinicalData> addClinicalData(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        // Calls the addClinicalData method in the ClinicalDataService to process and save the clinical data.
        // Returns the saved clinical data entity wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(clinicalDataService.addClinicalData(clinicalDataDTO));
    }

    /**
     * Endpoint to retrieve clinical records for a specific patient.
     * This method handles HTTP GET requests to the "/clinical-data/{patientId}" URL.
     *
     * @param patientId The ID of the patient whose clinical records are to be retrieved.
     * @return ResponseEntity<List<ClinicalDataDTO>> Returns a list of clinical records for the specified patient with an HTTP 200 OK status.
     */
    @GetMapping("/{patientId}") // Maps HTTP GET requests to this method, with {patientId} as a path variable.
    public ResponseEntity<List<ClinicalDataDTO>> getClinicalRecordsByPatient(@PathVariable Long patientId) {
        // Calls the getClinicalRecordsByPatient method in the ClinicalDataService to fetch clinical records for the specified patient.
        // Returns the list of clinical records wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok(clinicalDataService.getClinicalRecordsByPatient(patientId));
    }

    /**
     * Endpoint to display a welcome message.
     * This method handles HTTP GET requests to the "/clinical-data" URL.
     *
     * @return ResponseEntity<String> Returns a welcome message with an HTTP 200 OK status.
     */
    @GetMapping // Maps HTTP GET requests to this method.
    public ResponseEntity<String> getAllClinicalData() {
        // Returns a simple welcome message wrapped in a ResponseEntity with an HTTP 200 OK status.
        return ResponseEntity.ok("Welcome to Clinical Data");
    }
}