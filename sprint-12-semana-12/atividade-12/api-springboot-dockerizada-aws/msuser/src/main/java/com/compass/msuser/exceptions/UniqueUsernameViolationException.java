package com.compass.msuser.exceptions;

public class UniqueUsernameViolationException extends RuntimeException {
    public UniqueUsernameViolationException(String message) {
        super(message);
    }
}
