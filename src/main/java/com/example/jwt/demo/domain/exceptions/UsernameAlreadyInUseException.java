package com.example.jwt.demo.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(String message, Object... objects) {
        super(String.format(message, objects));
    }
}