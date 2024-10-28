package com.compass.aws_springboot.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseUserDTO {

    private String username;
    private String email;
    private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
}
