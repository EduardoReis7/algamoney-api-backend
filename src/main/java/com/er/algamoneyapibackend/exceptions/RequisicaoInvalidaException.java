package com.er.algamoneyapibackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoInvalidaException extends RuntimeException {
    public RequisicaoInvalidaException(String message) {
        super(message);
    }
}
