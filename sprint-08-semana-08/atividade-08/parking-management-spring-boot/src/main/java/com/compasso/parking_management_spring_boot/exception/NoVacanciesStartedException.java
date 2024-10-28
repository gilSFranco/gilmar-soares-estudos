package com.compasso.parking_management_spring_boot.exception;

public class NoVacanciesStartedException extends RuntimeException{

    public NoVacanciesStartedException(String message){
        super(message);
    }

}
