package com.mballem.demo_park_api.exception;

import lombok.Getter;

@Getter
public class UsernameUniqueViolationException extends RuntimeException {
    private String recurso;
    private String usuario;

    public UsernameUniqueViolationException(String message) {
        super(message);
    }

    public UsernameUniqueViolationException(String recurso, String usuario) {
        this.recurso = recurso;
        this.usuario = usuario;
    }
}
