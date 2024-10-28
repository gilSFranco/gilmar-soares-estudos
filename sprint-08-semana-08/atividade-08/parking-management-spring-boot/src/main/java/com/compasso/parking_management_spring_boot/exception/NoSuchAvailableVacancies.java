package com.compasso.parking_management_spring_boot.exception;

public class NoSuchAvailableVacancies extends RuntimeException{

    public NoSuchAvailableVacancies(String message){
        super(message);
    }
}
