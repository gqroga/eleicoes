package com.lifters.voter.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CandidatoComVotoException extends RuntimeException{
    public CandidatoComVotoException(String message) {super(message);}
}
