package com.example.rateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughCurrencyException extends RuntimeException {

    public NotEnoughCurrencyException() {
    }

    public NotEnoughCurrencyException(String message) {
        super(message);
    }
}
