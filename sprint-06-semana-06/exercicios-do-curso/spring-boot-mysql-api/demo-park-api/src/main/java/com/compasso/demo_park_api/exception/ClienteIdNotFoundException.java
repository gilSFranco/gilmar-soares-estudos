package com.compasso.demo_park_api.exception;

import lombok.Getter;

@Getter
public class ClienteIdNotFoundException extends RuntimeException {

    private String recurso;
    private Long id;

    public ClienteIdNotFoundException(String recurso, Long id) {
        this.recurso = recurso;
        this.id = id;
    }
}
