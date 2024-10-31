package com.compass.aws_springboot.exceptions;

public class AuthenticationNotCompleteException extends RuntimeException {
    public AuthenticationNotCompleteException(String message) {
        super(message);
    }
}
