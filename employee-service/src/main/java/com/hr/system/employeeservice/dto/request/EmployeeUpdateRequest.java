package com.hr.system.employeeservice.dto.request;

import com.hr.system.employeeservice.model.enums.EmployeePosition;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

import static com.hr.system.employeeservice.utill.ApplicationConstants.BASE_SALARY_POSITIVE_OR_ZERO;

public record EmployeeUpdateRequest(
        String firstName,

        String lastName,

        String email,

        EmployeePosition position,

        @PositiveOrZero(message = BASE_SALARY_POSITIVE_OR_ZERO)
        BigDecimal baseSalary) {
}
