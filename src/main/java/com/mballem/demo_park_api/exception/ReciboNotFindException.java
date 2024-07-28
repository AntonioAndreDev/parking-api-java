package com.mballem.demo_park_api.exception;

import lombok.Getter;

@Getter
public class ReciboNotFindException extends RuntimeException {
    private String recurso;
    private String recibo;

    public ReciboNotFindException(String message) {
        super(message);
    }

    public ReciboNotFindException(String recurso, String recibo) {
        this.recurso = recurso;
        this.recibo = recibo;
    }


}
