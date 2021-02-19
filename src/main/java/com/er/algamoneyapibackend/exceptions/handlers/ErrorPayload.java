package com.er.algamoneyapibackend.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorPayload {

    private String message;
    private Date date;
    private int status;
}
