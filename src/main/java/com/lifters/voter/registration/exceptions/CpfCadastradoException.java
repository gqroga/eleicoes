package com.lifters.voter.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfCadastradoException extends RuntimeException {

    public CpfCadastradoException(String massage) {
        super(massage);
    }
}
