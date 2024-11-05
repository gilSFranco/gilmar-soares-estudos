package com.compasso.msnotify.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotifyDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String operation;
}
