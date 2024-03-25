package com.lifters.voter.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidatosException extends RuntimeException{

    public CandidatosException(String message) {super(message);}
}
