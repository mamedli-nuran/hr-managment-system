package com.hr.system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    GLOBAL_ERROR_MESSAGE("Unexpected error occurred"),
    VALIDATION_FAILED("Validation Failed"),
    INVALID_REQUEST_BODY("Invalid request body"),
    UUID_NOT_VALID_FORMAT("Parameter '%s' should be of type %s. Value '%s' is invalid"),
    EMPLOYEE_NOT_FOUND("Employee with ID: %s not found"),
    LEAVE_NOT_FOUND("Leave with ID: %s not found"),
    LEAVE_ALREADY_PROCESSED("Leave with ID: %s is already processed with status %s"),
    EMPLOYEE_SERVICE_UNAVAILABLE("Employee service is unavailable. Please try again later");

    private final String message;
}
