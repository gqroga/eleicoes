package com.lifters.voter.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EleitorComVotoException extends RuntimeException {

    public EleitorComVotoException(String message) { super(message); }
}
