package com.compass.msuser.exceptions;

public class MessageNotSendException extends RuntimeException {
    public MessageNotSendException(String message) {
        super(message);
    }
}
