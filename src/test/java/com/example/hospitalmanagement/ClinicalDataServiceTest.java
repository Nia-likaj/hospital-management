package com.example.hospitalmanagement;

import com.example.hospitalmanagement.dto.ClinicalDataDTO;
import com.example.hospitalmanagement.entity.ClinicalData;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.ClinicalDataRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import com.example.hospitalmanagement.service.ClinicalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ClinicalDataServiceTest {

    // Mock the ClinicalDataRepository to simulate database operations
    @Mock
    private ClinicalDataRepository clinicalDataRepository;

    // Mock the PatientRepository to simulate database operations
    @Mock
    private PatientRepository patientRepository;

    // Inject the mocks into the ClinicalDataService instance being tested
    @InjectMocks
    private ClinicalDataService clinicalDataService;

    // This method runs before each test to initialize mocks
    @BeforeEach
    void setUp() {
        openMocks(this); // Initialize mocks
    }

    // Test case: Successfully add clinical data for a patient
    @Test
    void testAddClinicalData_Success() {
        // Arrange: Set up test data and mock behavior
        ClinicalDataDTO clinicalDataDTO = new ClinicalDataDTO(1L, "Patient has diabetes."); // Clinical data DTO

        Patient patient = new Patient();
        patient.setId(1L); // Set patient ID

        ClinicalData clinicalData = new ClinicalData(patient, "Patient has diabetes."); // Expected clinical data

        // Mock repository behavior
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient)); // Return patient when searched
        when(clinicalDataRepository.save(any(ClinicalData.class))).thenReturn(clinicalData); // Return clinical data when saved

        // Act: Call the service method
        ClinicalData result = clinicalDataService.addClinicalData(clinicalDataDTO);

        // Assert: Verify the result
        assertNotNull(result, "Clinical data should not be null"); // Verify result is not null
        assertEquals("Patient has diabetes.", result.getClinicalRecord(), "Clinical record should match"); // Verify clinical record

        // Verify interactions: Ensure repositories were called as expected
        verify(patientRepository, times(1)).findById(1L); // Verify patient was searched
        verify(clinicalDataRepository, times(1)).save(any(ClinicalData.class)); // Verify clinical data was saved
    }

    // Test case: Fail to add clinical data because the patient is not found
    @Test
    void testAddClinicalData_PatientNotFound() {
        // Arrange: Set up test data and mock behavior
        ClinicalDataDTO clinicalDataDTO = new ClinicalDataDTO(2L, "Patient has high blood pressure."); // Clinical data DTO

        // Mock repository behavior (patient not found)
        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert: Call the service method and expect an exception
        Exception exception = assertThrows(RuntimeException.class, () -> clinicalDataService.addClinicalData(clinicalDataDTO));
        assertEquals("Patient not found", exception.getMessage(), "Exception message should match"); // Verify exception message

        // Verify interactions: Ensure repositories were called as expected
        verify(patientRepository, times(1)).findById(2L); // Verify patient was searched
        verify(clinicalDataRepository, never()).save(any(ClinicalData.class)); // Verify clinical data was not saved
    }
}