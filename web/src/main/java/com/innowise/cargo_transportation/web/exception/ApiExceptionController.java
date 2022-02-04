package com.innowise.cargo_transportation.web.exception;

import com.innowise.cargo_transportation.core.exception.LoginAlreadyExistException;
import com.innowise.cargo_transportation.core.exception.PassportAlreadyExistException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionController {

    private static final HttpStatus STATUS_CONFLICT = HttpStatus.CONFLICT;

    @ExceptionHandler(value = {DataIntegrityViolationException.class,})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(500, messages);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = {PassportAlreadyExistException.class})
    public ResponseEntity<Object> handleLoginOrPassportIdException(PassportAlreadyExistException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(409, messages);
        return new ResponseEntity<>(errorResponse, STATUS_CONFLICT);
    }

    @ExceptionHandler(value = {LoginAlreadyExistException.class})
    public ResponseEntity<Object> handleLoginOrPassportIdException(LoginAlreadyExistException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(409, messages);
        return new ResponseEntity<>(errorResponse, STATUS_CONFLICT);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(409, messages);
        return new ResponseEntity<>(errorResponse, STATUS_CONFLICT);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add("This id does not exist");
        ErrorResponse errorResponse = new ErrorResponse(409, messages);
        return new ResponseEntity<>(errorResponse, STATUS_CONFLICT);
    }
}
