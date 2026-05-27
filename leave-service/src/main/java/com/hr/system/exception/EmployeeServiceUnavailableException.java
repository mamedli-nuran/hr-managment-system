package com.hr.system.exception;

public class EmployeeServiceUnavailableException extends RuntimeException {
    public EmployeeServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
