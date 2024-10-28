package com.compasso.parking_management_spring_boot.web.dto.spot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpotDTO {

    @Min(value = 1, message = "The number must be positive and at least 1.")
    @Max(value = 999, message = "The number can have at most 3 digits.")
    private Integer monthly;

    @Min(value = 1, message = "The number must be positive and at least 1.")
    @Max(value = 999, message = "The number can have at most 3 digits.")
    private Integer oneTime;
}