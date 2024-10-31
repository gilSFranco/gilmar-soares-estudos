package com.compass.msuser.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 8, max = 8)
    private String cep;
}
