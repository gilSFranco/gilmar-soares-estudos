package com.compasso.parking_management_spring_boot.validation;

import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.exception.ValidationEnumException;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;

public class VehicleValidation {

    public static void validateEnumsVehicle(CreateVehicleDTO vehicleDTO) {

        String categoryDTO = vehicleDTO.getCategory();
        String typeDTO = vehicleDTO.getType();

        Category category = Category.valueOf(categoryDTO);
        TypeVehicle typeVehicle = TypeVehicle.valueOf(typeDTO);

        if(category == Category.MONTHLY){
            if((typeVehicle != TypeVehicle.MOTORCYCLE) && (typeVehicle != TypeVehicle.PASSENGERCAR)){
                throw new ValidationEnumException("MONTHLY vehicles can be just MOTORCYCLE or PASSENGERCAR type.");
            }
        }
        if(category == Category.ONETIME){
            if((typeVehicle != TypeVehicle.MOTORCYCLE) && (typeVehicle != TypeVehicle.PASSENGERCAR)){
                throw new ValidationEnumException("ONETIME vehicles can be just MOTORCYCLE or PASSENGERCAR type.");
            }
        }
        if(category == Category.DELIVERY && typeVehicle != TypeVehicle.DELIVERYTRUCK){
            throw new ValidationEnumException("DELIVERY vehicles canc be just DELIVERYTRUCK type.");
        }
        if(category == Category.PUBLIC && typeVehicle != TypeVehicle.PUBLICSERVICE){
            throw new ValidationEnumException("PUBLIC vehicles canc be just PUBLICSERVICE type.");
        }

    }
}