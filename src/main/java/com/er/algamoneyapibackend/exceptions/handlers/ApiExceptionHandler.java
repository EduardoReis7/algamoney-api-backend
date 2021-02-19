package com.er.algamoneyapibackend.exceptions.handlers;

import com.er.algamoneyapibackend.exceptions.NaoEncontradoException;
import com.er.algamoneyapibackend.exceptions.RequisicaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<?> handleNaoEncontradoException(NaoEncontradoException ex) {

        ErrorPayload errorPayload = new ErrorPayload(ex.getLocalizedMessage(),
                new Date(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(errorPayload.getStatus()).body(errorPayload);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<?> handleRequisicaoInvalidaException(RequisicaoInvalidaException ex) {

        ErrorPayload errorPayload = new ErrorPayload(ex.getLocalizedMessage(),
                new Date(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(errorPayload.getStatus()).body(errorPayload);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException ex) {

        ErrorPayload errorPayload = new ErrorPayload(ex.getBindingResult().getFieldError().getDefaultMessage(),
                new Date(), HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(errorPayload.getStatus()).body(errorPayload);
    }
}
