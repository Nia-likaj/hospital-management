package com.example.hospitalmanagement;

import com.example.hospitalmanagement.dto.PatientDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import com.example.hospitalmanagement.repository.PatientRepository;
import com.example.hospitalmanagement.service.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private PatientService patientService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Ensures resources are closed after each test
    }


    @Test
    void testSavePatient_Success() {
        PatientDTO patientDTO = new PatientDTO("John", "Doe", LocalDate.of(1990, 5, 15), 1L, null);
        Department department = new Department("CARD", "Cardiology");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient("John", "Doe", LocalDate.of(1990, 5, 15), department));

        Patient result = patientService.savePatient(patientDTO);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }
}
