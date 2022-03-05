package com.innowise.cargo_transportation.core.dto.response;

import com.innowise.cargo_transportation.core.entity.CarEntity;
import com.innowise.cargo_transportation.core.entity.CarType;
import lombok.Data;

@Data
public class CarResponse {
    private Long id;
    private String number;
    private int fuelConsumption;
    private int loadCapacity;
    private CarType type;

    public CarResponse(CarEntity car){
        id = car.getId();
        number = car.getNumber();
        fuelConsumption = car.getFuelConsumption();
        loadCapacity = car.getLoadCapacity();
        type = car.getCarType();
    }
}
