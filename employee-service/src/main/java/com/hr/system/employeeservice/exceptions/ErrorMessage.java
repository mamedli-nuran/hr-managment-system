package com.hr.system.employeeservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    EMAIL_ALREADY_EXISTS("Email already exists");

    private final String value;
}
