package com.hr.system.employeeservice.repository;

import com.hr.system.employeeservice.model.Employee;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);
    boolean existsById(@NonNull UUID id);
}
