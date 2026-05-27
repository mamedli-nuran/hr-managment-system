package com.hr.system.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    EMPLOYEE_NOT_FOUND("Employee with ID: %s not found"),
    GLOBAL_ERROR_MESSAGE("Unexcepted error occurred by Nuran team"),
    UUID_NOT_VALID_FORMAT("Parameter '%s' should be of type %s. Value '%s' is invalid"),
    VALIDATION_FAILED("Validation Failed"),
    INVALID_REQUEST_BODY("Invalid request body");

    private final String message;
}
