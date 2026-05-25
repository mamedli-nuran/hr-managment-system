package com.hr.system.employeeservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    GLOBAL_ERROR_MESSAGE("Unexcepted error occurred by Nuran team"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    EMPLOYEE_NOT_FOUND("Employee with ID: %s not found"),
    UUID_NOT_VALID_FORMAT("Parameter '%s' should be of type %s. Value '%s' is invalid");



    private final String value;
}
