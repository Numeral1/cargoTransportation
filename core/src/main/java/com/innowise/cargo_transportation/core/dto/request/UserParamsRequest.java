package com.innowise.cargo_transportation.core.dto.request;

import jdk.jfr.Timestamp;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserParamsRequest {
    private int pageNumber;
    private int pageSize = 3;
    private String name;
    private String surname;
    private String patronymic;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beforeBornDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate afterBornDate;
    private String town;
    private String street;
    private String house;
    private String flat;


}
