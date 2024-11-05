package com.compass.msuser.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    @NotBlank(message = "Username cannot be blank or null")
    @Size(min = 2, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank or null")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must have at least 8 characters, an uppercase letter, a lowercase letter and a number"
    )
    private String password;

    @NotBlank(message = "Email cannot be blank or null")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Cep cannot be blank or null")
    @Pattern(regexp = "\\d{8}", message = "Cep must have at least 8 characters and only numbers")
    private String cep;
}
