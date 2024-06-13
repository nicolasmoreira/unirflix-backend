package com.unir.rents.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieAlreadyPurchasedException extends RuntimeException {
    public MovieAlreadyPurchasedException(String message) {
        super(message);
    }
}