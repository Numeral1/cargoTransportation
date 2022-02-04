package com.innowise.cargo_transportation.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final Instant timestamp;
    private final int status;
    private final List<String> messages;

    public ErrorResponse(int status, List<String> messages) {
        timestamp = Instant.now();
        this.status = status;
        this.messages = messages;

    }

    public static ErrorResponse of(int httpStatus, String... message) {
        return new ErrorResponse( httpStatus,new LinkedList<>(Arrays.asList(message)));
    }
}