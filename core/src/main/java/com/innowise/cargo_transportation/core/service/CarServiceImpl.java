package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.CarParamRequest;
import com.innowise.cargo_transportation.core.dto.request.CarRequest;
import com.innowise.cargo_transportation.core.dto.response.CarListResponse;
import com.innowise.cargo_transportation.core.dto.response.CarResponse;
import com.innowise.cargo_transportation.core.entity.CarEntity;
import com.innowise.cargo_transportation.core.entity.QCarEntity;
import com.innowise.cargo_transportation.core.repository.CarRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@Service
public class CarServiceImpl implements CarService{
    private final CarRepository repository;

    @Transactional
    @Override
    public Long createCar(CarRequest carRequest) {
       CarEntity entity = carRequest.toEntity();
       repository.save(entity);
       return entity.getId();
    }

    @Transactional
    @Override
    public void updateCar(long id, CarRequest carRequest) {
        CarEntity entity = carRequest.toEntity();
        entity.setId(id);
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public CarResponse findCarById(long id) {
        CarEntity car = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car with id: " + id + "does not exists"));
        return new CarResponse(car);
    }

    @Transactional(readOnly = true)
    @Override
    public CarListResponse findList(CarParamRequest carParamRequest, Pageable pageable) {
        BooleanBuilder booleanBuilder = buildWhere(carParamRequest);
        Page<CarEntity> page = repository.findAll(booleanBuilder, pageable);
        return new CarListResponse(page.map(CarResponse::new).getContent(), page.getTotalElements());
    }

    private BooleanBuilder buildWhere(CarParamRequest paramRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(paramRequest.getNumber() != null){
            booleanBuilder.and(QCarEntity.carEntity.number.like("%" + paramRequest.getNumber() + "%"));
        }
        if(paramRequest.getFuelConsumptionLess() != null && paramRequest.getFuelConsumptionMore() != null){
            booleanBuilder.and(QCarEntity.carEntity.fuelConsumption.between(paramRequest.getFuelConsumptionLess(), paramRequest.getFuelConsumptionMore()));
        }
        if(paramRequest.getFuelConsumptionLess() == null && paramRequest.getFuelConsumptionMore() != null){
            booleanBuilder.and(QCarEntity.carEntity.fuelConsumption.between(0, paramRequest.getFuelConsumptionMore()));
        }
        if(paramRequest.getFuelConsumptionLess() != null && paramRequest.getFuelConsumptionMore() == null){
            booleanBuilder.and(QCarEntity.carEntity.fuelConsumption.between(paramRequest.getFuelConsumptionLess(), 200));
        }
        if(paramRequest.getLoadCapacityMore() != null && paramRequest.getLoadCapacityLess() != null){
            booleanBuilder.and(QCarEntity.carEntity.loadCapacity.between(paramRequest.getLoadCapacityLess(), paramRequest.getLoadCapacityMore()));
        }
        if(paramRequest.getLoadCapacityMore() != null && paramRequest.getLoadCapacityLess() == null){
            booleanBuilder.and(QCarEntity.carEntity.loadCapacity.between(0, paramRequest.getLoadCapacityMore()));
        }
        if(paramRequest.getLoadCapacityMore() == null && paramRequest.getLoadCapacityLess() != null){
            booleanBuilder.and(QCarEntity.carEntity.loadCapacity.between(paramRequest.getLoadCapacityLess(), Integer.MAX_VALUE));
        }
        if (paramRequest.getType() != null){
            booleanBuilder.and(QCarEntity.carEntity.carType.eq(paramRequest.getType()));
        }
        return booleanBuilder;
    }

    @Transactional
    @Override
    public void deleteCar(List<Long> id) {
        repository.deleteAllById(id);
    }
}
