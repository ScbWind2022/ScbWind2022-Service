package com.example.userservice.exception;

public class UserInSessionException extends RuntimeException {
    public UserInSessionException() {
        super("User in session");
    }

    public UserInSessionException(String message) {
        super(message);
    }

    public UserInSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInSessionException(Throwable cause) {
        super(cause);
    }
}
