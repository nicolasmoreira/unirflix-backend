package com.unir.rents.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieAlreadyReturnedException extends RuntimeException {
    public MovieAlreadyReturnedException(String message) {
        super(message);
    }
}