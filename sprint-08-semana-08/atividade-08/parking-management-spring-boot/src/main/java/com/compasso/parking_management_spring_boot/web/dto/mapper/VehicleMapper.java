package com.compasso.parking_management_spring_boot.web.dto.mapper;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.UpdatePaymentDTO;
import org.modelmapper.ModelMapper;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {

    public static Vehicle toVehicle(CreateVehicleDTO createDTO){
        return new ModelMapper().map(createDTO, Vehicle.class);
    }

    public static Vehicle toVehiclePayment(UpdatePaymentDTO paymentDTO){
        return new ModelMapper().map(paymentDTO, Vehicle.class);
    }

    public static ResponseVehicleDTO toDTO(Vehicle vehicle){
        return new ModelMapper().map(vehicle, ResponseVehicleDTO.class);
    }

    public static List<ResponseVehicleDTO> toListDTO(List<Vehicle> vehicleList){
        return vehicleList.stream().map(vehicle -> toDTO(vehicle)).collect(Collectors.toList());
    }

    public static List<ResponseVehicleDTO> toListDTO(Optional<Vehicle> vehicleList){
        return vehicleList.stream().map(vehicle -> toDTO(vehicle)).collect(Collectors.toList());
    }

    public static ResponseVehicleDTO toDTO(Optional<Vehicle> v){
        return new ModelMapper().map(v, ResponseVehicleDTO.class);
    }


}
