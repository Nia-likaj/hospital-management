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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    // Mock the DepartmentRepository to simulate database operations
    @Mock
    private DepartmentRepository departmentRepository;

    // Inject the mocks into the DepartmentService instance being tested
    @InjectMocks
    private DepartmentService departmentService;

    // Used to release resources after tests
    private AutoCloseable closeable;

    // This method runs before each test to initialize mocks
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // This method runs after each test to release resources
    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // Release resources
    }

    // Test case: Successfully save a department
    @Test
    void testSaveDepartment_Success() {
        // Arrange: Set up test data and mock behavior
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "CARD", "Cardiology"); // Department DTO
        Department department = new Department("CARD", "Cardiology"); // Expected department

        // Mock repository behavior
        when(departmentRepository.save(any(Department.class))).thenReturn(department); // Return department when saved

        // Act: Call the service method
        Department result = departmentService.saveDepartment(departmentDTO);

        // Assert: Verify the result
        assertNotNull(result, "Department should not be null"); // Verify result is not null
        assertEquals("CARD", result.getCode(), "Department code should match"); // Verify department code
        assertEquals("Cardiology", result.getName(), "Department name should match"); // Verify department name

        // Verify interactions: Ensure repository was called as expected
        verify(departmentRepository, times(1)).save(any(Department.class)); // Verify department was saved
    }

    // Test case: Successfully retrieve all departments
    @Test
    void testGetAllDepartments() {
        // Arrange: Set up test data and mock behavior
        List<Department> departments = List.of(
                new Department("CARD", "Cardiology"), // First department
                new Department("NEUR", "Neurology") // Second department
        );

        // Mock repository behavior
        when(departmentRepository.findAll()).thenReturn(departments); // Return list of departments

        // Act: Call the service method
        List<DepartmentDTO> result = departmentService.getAllDepartments();

        // Assert: Verify the result
        assertEquals(2, result.size(), "There should be two departments"); // Verify number of departments
        assertEquals("Cardiology", result.get(0).getName(), "First department name should match"); // Verify first department name
        assertEquals("Neurology", result.get(1).getName(), "Second department name should match"); // Verify second department name

        // Verify interactions: Ensure repository was called as expected
        verify(departmentRepository, times(1)).findAll(); // Verify departments were retrieved
    }
}