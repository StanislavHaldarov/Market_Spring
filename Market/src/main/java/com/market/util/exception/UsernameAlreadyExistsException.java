package com.market.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
