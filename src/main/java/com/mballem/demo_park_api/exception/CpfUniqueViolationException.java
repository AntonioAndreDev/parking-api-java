package com.mballem.demo_park_api.exception;

import lombok.Getter;

@Getter
public class CpfUniqueViolationException extends RuntimeException {
    private String recurso;
    private String cpf;

    public CpfUniqueViolationException(String message) {
        super(message);
    }

    public CpfUniqueViolationException(String recurso, String cpf) {
        this.recurso = recurso;
        this.cpf = cpf;
    }
}
