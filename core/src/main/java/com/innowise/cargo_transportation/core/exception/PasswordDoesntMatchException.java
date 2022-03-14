package com.innowise.cargo_transportation.core.exception;

public class PasswordDoesntMatchException extends RuntimeException{
    public PasswordDoesntMatchException(String message) {
        super(message);
    }

    public PasswordDoesntMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
