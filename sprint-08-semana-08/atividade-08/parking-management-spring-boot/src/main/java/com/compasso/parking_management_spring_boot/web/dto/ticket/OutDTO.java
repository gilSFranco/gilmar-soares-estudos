package com.compasso.parking_management_spring_boot.web.dto.ticket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutDTO {

    @NotNull(message = "The entrance gate cannot be null")
    @Min(value = 6, message = "The exit gates are from 5 to 10.")
    @Max(value = 10, message = "The exit gates are from 5 to 10.")
    private Integer out;

    @JsonCreator
    public OutDTO(@JsonProperty("out") Integer out) {
        this.out = out;
    }
}
