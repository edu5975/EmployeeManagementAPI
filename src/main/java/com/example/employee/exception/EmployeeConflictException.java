package com.example.employee.exception;

public class EmployeeConflictException extends RuntimeException {

    public EmployeeConflictException(String message) {
        super(message);
    }

    public EmployeeConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
