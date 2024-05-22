package com.dev.dino.demoparkapi.entity.exception;

public class CodigoUniqueViolationException extends RuntimeException{
    public CodigoUniqueViolationException(String msg){
        super(msg);
    }
}
