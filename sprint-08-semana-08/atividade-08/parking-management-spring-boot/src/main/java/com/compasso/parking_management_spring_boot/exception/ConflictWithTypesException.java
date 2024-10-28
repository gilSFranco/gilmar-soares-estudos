package com.compasso.parking_management_spring_boot.exception;

public class ConflictWithTypesException extends RuntimeException{

    public ConflictWithTypesException(String message){
        super(message);
    }
}