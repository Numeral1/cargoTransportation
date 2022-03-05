package com.innowise.cargo_transportation.core.dto.request;

import com.innowise.cargo_transportation.core.entity.CarEntity;
import com.innowise.cargo_transportation.core.entity.CarType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CarRequest {
    @NotNull(message = "Car number can not be null")
    @Size(max = 10, message = "Car number must contain less than 10 characters")
    private String number;
    @NotNull(message = "Car fuel consumption can not be null")
    private int fuelConsumption;
    @NotNull(message = "Car load capacity can not be null")
    private int loadCapacity;
    @NotNull(message = "Car type can not be null")
    private CarType type;
    private Long clientId;

    public CarEntity toEntity(){
        CarEntity carEntity = new CarEntity();
        carEntity.setNumber(number);
        carEntity.setFuelConsumption(fuelConsumption);
        carEntity.setLoadCapacity(loadCapacity);
        carEntity.setCarType(type);

        return carEntity;
    }
}
