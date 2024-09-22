package com.compasso.api_rest_name.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {

    @NotBlank
    @Size(min = 5, max = 100)
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    private Integer age;
}
