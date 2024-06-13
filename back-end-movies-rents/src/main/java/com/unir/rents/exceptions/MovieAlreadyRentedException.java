package com.unir.rents.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieAlreadyRentedException extends RuntimeException {
    public MovieAlreadyRentedException(String message) {
        super(message);
    }
}