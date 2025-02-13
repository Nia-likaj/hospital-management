package com.example.hospitalmanagement;

import com.example.hospitalmanagement.dto.DepartmentDTO;
import com.example.hospitalmanagement.entity.Department;
import com.example.hospitalmanagement.repository.DepartmentRepository;
import com.example.hospitalmanagement.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

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
    void testSaveDepartment_Success() {
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "CARD", "Cardiology");
        Department department = new Department("CARD", "Cardiology");

        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department result = departmentService.saveDepartment(departmentDTO);

        assertNotNull(result);
        assertEquals("CARD", result.getCode());
        assertEquals("Cardiology", result.getName());
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void testGetAllDepartments() {
        List<Department> departments = List.of(
                new Department("CARD", "Cardiology"),
                new Department("NEUR", "Neurology")
        );

        when(departmentRepository.findAll()).thenReturn(departments);

        List<DepartmentDTO> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
        assertEquals("Cardiology", result.get(0).getName());
        assertEquals("Neurology", result.get(1).getName());
        verify(departmentRepository, times(1)).findAll();
    }
}
