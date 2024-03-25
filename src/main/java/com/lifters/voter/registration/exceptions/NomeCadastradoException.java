package com.lifters.voter.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NomeCadastradoException extends RuntimeException {

    public NomeCadastradoException(String massage) {
        super(massage);
    }
}
