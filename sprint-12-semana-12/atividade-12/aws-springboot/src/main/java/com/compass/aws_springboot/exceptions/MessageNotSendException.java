package com.compass.aws_springboot.exceptions;

public class MessageNotSendException extends RuntimeException {
    public MessageNotSendException(String message) {
        super(message);
    }
}
