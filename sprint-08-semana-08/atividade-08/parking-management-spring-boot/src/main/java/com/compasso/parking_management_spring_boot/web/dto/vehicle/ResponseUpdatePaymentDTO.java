package com.compasso.parking_management_spring_boot.web.dto.vehicle;

import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseUpdatePaymentDTO {

    private String licensePlate;
    private TypeVehicle typeVehicle;
    private LocalDateTime paymentDate;
}
