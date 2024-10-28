package com.compasso.parking_management_spring_boot.exception;

public class VehicleAlreadyParkedException extends RuntimeException {

    public VehicleAlreadyParkedException(String message) {
        super(message);

    }
}