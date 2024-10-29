package com.compass.aws_springboot.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdatePasswordDTO {

    @NotBlank(message = "Username cannot be blank or null")
    private String username;

    @NotBlank(message = "Old Password cannot be blank or null")
    private String oldPassword;

    @NotBlank(message = "New Password cannot be blank or null")
    private String newPassword;
}
