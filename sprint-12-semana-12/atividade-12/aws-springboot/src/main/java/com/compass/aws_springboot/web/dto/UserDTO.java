package com.compass.aws_springboot.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    @NotBlank(message = "Username cannot be blank or null")
    private String username;

    @NotBlank(message = "Password cannot be blank or null")
    private String password;

    @NotBlank(message = "Email cannot be blank or null")
    private String email;

    @NotBlank(message = "Cep cannot be blank or null")
    private String cep;
}
