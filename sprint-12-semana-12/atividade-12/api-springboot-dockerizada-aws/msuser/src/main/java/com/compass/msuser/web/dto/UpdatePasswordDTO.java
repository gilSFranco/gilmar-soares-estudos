package com.compass.msuser.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePasswordDTO {

    @NotBlank(message = "Username cannot be blank or null")
    @Size(min = 2, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Old Password cannot be blank or null")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Old Password must have at least 8 characters, an uppercase letter, a lowercase letter and a number"
    )
    private String oldPassword;

    @NotBlank(message = "New Password cannot be blank or null")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "New Password must have at least 8 characters, an uppercase letter, a lowercase letter and a number"
    )
    private String newPassword;
}
