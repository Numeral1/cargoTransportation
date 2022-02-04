package com.innowise.cargo_transportation.core.exception;

public class LoginAlreadyExistException extends RuntimeException{
    public LoginAlreadyExistException(String message) {
        super(message);
    }

    public LoginAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
