package com.hr.system.employeeservice.dto.response;

import com.hr.system.employeeservice.model.enums.EmployeePosition;
import com.hr.system.employeeservice.model.enums.EmployeeStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
public record EmployeeInfoResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        EmployeePosition position,
        BigDecimal baseSalary,
        LocalDate hireDate,
        EmployeeStatus status
) {
}

