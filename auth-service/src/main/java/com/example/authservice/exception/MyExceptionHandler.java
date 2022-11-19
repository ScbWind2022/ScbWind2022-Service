package com.example.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    private ResponseEntity<Object> constructException(final String message, final HttpStatus httpStatus){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message",message);
        return new ResponseEntity<>(body,httpStatus);
    }
    @ExceptionHandler(value = {NotValidRequestException.class})
    public ResponseEntity<Object> handleNotValidRequestException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {IncorrectJwtTokenException.class})
    public ResponseEntity<Object> handleIncorrectJwtTokenException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
