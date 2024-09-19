package com.compasso.demo_park_api.exception;

import lombok.Getter;

@Getter
public class ClienteCpfNotFoundException extends RuntimeException {

    private String recurso;
    private String cpf;

    public ClienteCpfNotFoundException(String recurso, String cpf) {
        this.recurso = recurso;
        this.cpf = cpf;
    }
}
