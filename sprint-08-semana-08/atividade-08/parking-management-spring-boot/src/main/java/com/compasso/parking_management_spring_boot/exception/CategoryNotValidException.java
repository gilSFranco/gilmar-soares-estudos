package com.compasso.parking_management_spring_boot.exception;

public class CategoryNotValidException extends RuntimeException {
    public CategoryNotValidException(String format) {
        super(format);
    }
}
