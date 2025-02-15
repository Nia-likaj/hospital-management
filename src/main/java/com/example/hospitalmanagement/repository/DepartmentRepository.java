package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface is a Spring Data JPA repository for the `Department` entity.
 * It provides methods to perform database operations related to hospital departments.
 */
@Repository // Marks this interface as a Spring Data repository, making it a managed bean in the Spring context.
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // No additional methods are defined here, but the repository inherits all CRUD methods from JpaRepository.
}