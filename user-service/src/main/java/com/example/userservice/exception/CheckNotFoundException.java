package com.example.userservice.exception;

public class CheckNotFoundException extends RuntimeException{
    public CheckNotFoundException() {
        super();
    }

    public CheckNotFoundException(String message) {
        super(message);
    }

    public CheckNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckNotFoundException(Throwable cause) {
        super(cause);
    }
}
