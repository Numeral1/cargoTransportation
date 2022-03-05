package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.CarType;
import lombok.Data;

@Data
public class CarParamRequest {
    private int pageNumber;
    private int pageSize;
    private String number;
    private Integer fuelConsumptionLess;
    private Integer fuelConsumptionMore;
    private Integer loadCapacityLess;
    private Integer LoadCapacityMore;
    private CarType type;
}
