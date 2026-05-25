package com.hr.system.employeeservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    EMAIL_ALREADY_EXISTS("Email already exists"),
    EMPLOYEE_NOT_FOUND("Employee with ID: %s not found"),
    UUID_NOT_VALID_FORMAT("Parameter '%s' should be of type %s. Value '%s' is invalid");



    private final String value;
}
