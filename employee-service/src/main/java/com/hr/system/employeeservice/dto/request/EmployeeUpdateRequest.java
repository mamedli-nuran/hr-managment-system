package com.hr.system.employeeservice.dto.request;

import com.hr.system.employeeservice.model.enums.EmployeePosition;

public record EmployeeUpdateRequest(
        String firstName,

        String lastName,

        String email,

        EmployeePosition position) {
}