package com.compasso.parking_management_spring_boot.exception;

public class PlateUniqueViolationException extends RuntimeException{

    public PlateUniqueViolationException(String message){
        super(message);
    }

}
