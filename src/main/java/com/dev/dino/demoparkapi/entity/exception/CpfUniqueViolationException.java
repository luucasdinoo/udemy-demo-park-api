package com.dev.dino.demoparkapi.entity.exception;

public class CpfUniqueViolationException extends RuntimeException{

    public CpfUniqueViolationException(String msg) {
        super(msg);
    }
}
