package com.hr.system.employeeservice.dto.request;

import com.hr.system.employeeservice.model.enums.EmployeePosition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

import static com.hr.system.employeeservice.utill.ApplicationConstants.*;


public record EmployeeCreateRequest(

        @NotBlank(message = FIRST_NAME_IS_REQUIRED)
        String firstName,

        @NotBlank(message = LAST_NAME_IS_REQUIRED)
        String lastName,

        @NotBlank(message = EMAIL_IS_REQUIRED)
        @Email(message = NOT_VALID_EMAIL)
        String email,

        @NotNull(message = EMPLOYEE_POSITION_IS_REQUIRED)
        EmployeePosition position,

        @NotNull(message = BASE_SALARY_IS_REQUIRED)
        @PositiveOrZero(message = BASE_SALARY_POSITIVE_OR_ZERO)
        BigDecimal baseSalary

) {
}
