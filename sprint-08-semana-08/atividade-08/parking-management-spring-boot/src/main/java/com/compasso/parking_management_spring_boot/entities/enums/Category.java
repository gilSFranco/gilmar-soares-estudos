package com.compasso.parking_management_spring_boot.entities.enums;

public enum Category {
    ONETIME,
    DELIVERY,
    MONTHLY,
    PUBLIC;

    public static Boolean isValidCategory(String categoryVehicle) {
        for(Category category : Category.values()) {
            if(category.name().equalsIgnoreCase(categoryVehicle)) {
                return true;
            }
        }

        return false;
    }
}
