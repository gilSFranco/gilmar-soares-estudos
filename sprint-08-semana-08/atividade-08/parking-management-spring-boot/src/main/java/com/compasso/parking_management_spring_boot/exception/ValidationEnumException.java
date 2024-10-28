package com.compasso.parking_management_spring_boot.exception;

public class ValidationEnumException extends RuntimeException {
    public ValidationEnumException(String format) {
        super(format);
    }
}
