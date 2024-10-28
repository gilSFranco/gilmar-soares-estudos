package com.compasso.parking_management_spring_boot.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message){
        super(message);
    }

}
