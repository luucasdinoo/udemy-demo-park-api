package com.dev.dino.demoparkapi.entity.exception;

public class UserNameUniqueViolationException extends RuntimeException {

    public UserNameUniqueViolationException(String msg) {
        super(msg);
    }
}
