package com.hr.system.authservice.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorMessage {
    AUTHENTICATION_FAILED("Authentication failed");

    private final String message;


}
