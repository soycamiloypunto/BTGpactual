package com.btgpactual.gestionfondos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FondoNoEncontradoException extends RuntimeException {
    public FondoNoEncontradoException(String message) {
        super(message);
    }
}