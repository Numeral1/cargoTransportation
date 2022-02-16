package com.innowise.cargo_transportation.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class StorageListResponse {
    private List<StorageResponse> content;
    private long totalElements;

}
