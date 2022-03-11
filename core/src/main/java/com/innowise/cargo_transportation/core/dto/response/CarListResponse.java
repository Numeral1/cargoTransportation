package com.innowise.cargo_transportation.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CarListResponse {
    private List<CarResponse> content;
    private long totalElements;
}
