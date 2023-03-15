package com.market.utility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException(String message){
        super(message);
    }
}
