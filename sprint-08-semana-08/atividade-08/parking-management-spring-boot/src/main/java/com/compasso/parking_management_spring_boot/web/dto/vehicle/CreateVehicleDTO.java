package com.compasso.parking_management_spring_boot.web.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateVehicleDTO {

    @NotBlank(message = "Type cannot be blank")
    @Pattern(
            regexp = "PASSENGERCAR|MOTORCYCLE|DELIVERYTRUCK|PUBLICSERVICE",
            message = "Valid types are: PASSENGERCAR, MOTORCYCLE, DELIVERYTRUCK, PUBLICSERVICE"
    )
    private String type;

    @NotBlank(message = "Category cannot be blank")
    @Pattern(
            regexp = "ONETIME|DELIVERY|MONTHLY|PUBLIC",
            message = "Valid categories are: ONETIME, DELIVERY, MONTHLY, PUBLIC"
    )
    private String category;

    @NotBlank
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The vehicle license plate must follow the standard in uppercase 'XXX-0000'")
    private String licensePlate;

}