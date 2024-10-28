package com.compasso.parking_management_spring_boot.entities.enums;

public enum TypeVehicle {
    PASSENGERCAR,
    MOTORCYCLE,
    DELIVERYTRUCK,
    PUBLICSERVICE;

    public static Boolean isValidType(String typeVehicle) {
        for(TypeVehicle type : TypeVehicle.values()) {
            if(type.name().equalsIgnoreCase(typeVehicle)) {
                return true;
            }
        }

        return false;
    }
}
