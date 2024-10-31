package com.compass.msuser.exceptions;

public class AuthenticationNotCompleteException extends RuntimeException {
    public AuthenticationNotCompleteException(String message) {
        super(message);
    }
}
