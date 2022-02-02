package com.innowise.cargo_transportation.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;

    public ErrorResponse(int status, List<String> errors) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
    }

    public static ErrorResponse of(int status, String... errors) {
        return new ErrorResponse(status, new LinkedList<>(Arrays.asList(errors)));
    }
}