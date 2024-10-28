package com.compasso.parking_management_spring_boot.web.dto.ticket;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class TicketDTO {

    @NotBlank(message = "Type cannot be blank")
    @Pattern(
            regexp = "PASSENGERCAR|MOTORCYCLE|DELIVERYTRUCK|PUBLICSERVICE",
            message = "Valid types are: PASSENGERCAR, MOTORCYCLE, DELIVERYTRUCK, PUBLICSERVICE"
    )
    private String type;

    @NotBlank
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "The vehicle license plate must follow the standard in uppercase 'XXX-0000'")
    private String licensePlate;

    @NotNull(message = "The entrance gate cannot be null")
    @Min(value = 1, message = "The entrance gates are from 1 to 5.")
    @Max(value = 5, message = "The entrance gates are from 1 to 5.")
    private int in;
}
