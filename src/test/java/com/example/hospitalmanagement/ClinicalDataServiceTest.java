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

    @Mock
    private ClinicalDataRepository clinicalDataRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private ClinicalDataService clinicalDataService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testAddClinicalData_Success() {
        ClinicalDataDTO clinicalDataDTO = new ClinicalDataDTO(1L, "Patient has diabetes.");

        Patient patient = new Patient();
        patient.setId(1L);

        ClinicalData clinicalData = new ClinicalData(patient, "Patient has diabetes.");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(clinicalDataRepository.save(any(ClinicalData.class))).thenReturn(clinicalData);

        ClinicalData result = clinicalDataService.addClinicalData(clinicalDataDTO);

        assertNotNull(result);
        assertEquals("Patient has diabetes.", result.getClinicalRecord());
        verify(patientRepository, times(1)).findById(1L);
        verify(clinicalDataRepository, times(1)).save(any(ClinicalData.class));
    }

    @Test
    void testAddClinicalData_PatientNotFound() {
        ClinicalDataDTO clinicalDataDTO = new ClinicalDataDTO(2L, "Patient has high blood pressure.");

        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> clinicalDataService.addClinicalData(clinicalDataDTO));
        assertEquals("Patient not found", exception.getMessage());

        verify(patientRepository, times(1)).findById(2L);
        verify(clinicalDataRepository, never()).save(any(ClinicalData.class));
    }
}
