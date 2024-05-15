package com.dev.dino.demoparkapi.entity.exception;

public class PasswordInvalidException extends RuntimeException {

    public PasswordInvalidException(String msg){
        super(msg);
    }
}
