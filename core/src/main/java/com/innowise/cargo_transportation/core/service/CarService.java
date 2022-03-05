package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.CarParamRequest;
import com.innowise.cargo_transportation.core.dto.request.CarRequest;
import com.innowise.cargo_transportation.core.dto.response.CarListResponse;
import com.innowise.cargo_transportation.core.dto.response.CarResponse;

import java.util.List;

public interface CarService {
    Long createCar(CarRequest carRequest);

    void updateCar(long id, CarRequest carRequest);

    void deleteCar(List<Long> id);

    CarResponse findCarById(long id);

    CarListResponse findList(CarParamRequest carParamRequest);
}
