package com.compasso.demo_park_api.exception;

import lombok.Getter;

@Getter
public class ReciboNotFoundException extends RuntimeException {

    private String recurso;
    private String recibo;

    public ReciboNotFoundException(String recurso, String recibo) {
        this.recurso = recurso;
        this.recibo = recibo;
    }
}
