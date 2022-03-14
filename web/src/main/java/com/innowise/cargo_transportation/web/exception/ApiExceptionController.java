package com.innowise.cargo_transportation.web.exception;

import com.innowise.cargo_transportation.core.exception.ApplicationException;
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

    @ExceptionHandler(value = {DataIntegrityViolationException.class,})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(status.value(), messages);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<Object> handleLoginOrPassportIdException(ApplicationException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(e.getHttpStatus().value(), messages);
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), messages);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        LinkedList<String> messages = new LinkedList<>();
        messages.add(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), messages);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
