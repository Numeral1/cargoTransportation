package com.innowise.cargo_transportation.core.exception;

public class PassportAlreadyExistException extends RuntimeException{

    public PassportAlreadyExistException(String message) {
        super(message);
    }

    public PassportAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }



}
