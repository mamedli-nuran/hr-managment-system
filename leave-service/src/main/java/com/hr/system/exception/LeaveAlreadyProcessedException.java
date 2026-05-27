package com.hr.system.exception;

public class LeaveAlreadyProcessedException extends RuntimeException {
    public LeaveAlreadyProcessedException(String message) {
        super(message);
    }
}
