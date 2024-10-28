package com.compasso.parking_management_spring_boot.web.dto.ticket;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseTicketDTO {

    private Long id;

    private Vehicle vehicle;

    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Integer entryGate;
    private Integer exitGate;
    private Double value;
    private String spots;
}
