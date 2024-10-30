package com.compass.aws_springboot.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceStatus {
    private String username;
    private String operation;
}
