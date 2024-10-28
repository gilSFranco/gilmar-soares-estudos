package com.compasso.parking_management_spring_boot.exception;

public class IvalidCancelException extends RuntimeException{

    public IvalidCancelException(String message){
        super(message);
    }
}