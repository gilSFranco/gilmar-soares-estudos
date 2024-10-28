package com.compasso.parking_management_spring_boot.web.dto.vehicle;

import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseVehicleDTO {

    private TypeVehicle type;
    private Category category;
    private String licensePlate;
}
