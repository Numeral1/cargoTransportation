package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.CarParamRequest;
import com.innowise.cargo_transportation.core.dto.request.CarRequest;
import com.innowise.cargo_transportation.core.dto.response.CarListResponse;
import com.innowise.cargo_transportation.core.dto.response.CarResponse;
import com.innowise.cargo_transportation.core.entity.CarEntity;
import com.innowise.cargo_transportation.core.entity.QCarEntity;
import com.innowise.cargo_transportation.core.repository.CarRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
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
    public CarListResponse findList(CarParamRequest carParamRequest) {
        Pageable pageable = PageRequest.of(carParamRequest.getPageNumber(), carParamRequest.getPageSize());
        BooleanBuilder booleanBuilder = buildWhere(carParamRequest);
        Page<CarEntity> page = repository.findAll(booleanBuilder, pageable);
        return new CarListResponse(page.map(CarResponse::new).getContent(), page.getTotalElements());
    }

    private BooleanBuilder buildWhere(CarParamRequest paramRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(paramRequest.getNumber() != null){
            booleanBuilder.and(QCarEntity.carEntity.number.like("%" + paramRequest.getNumber() + "%"));
        }
        if(paramRequest.getFuelConsumptionLess() != null){
            booleanBuilder.and(QCarEntity.carEntity.fuelConsumption.between(0, paramRequest.getFuelConsumptionLess()));
        }
        if(paramRequest.getFuelConsumptionMore() != null){
            booleanBuilder.and(QCarEntity.carEntity.fuelConsumption.between(paramRequest.getFuelConsumptionMore(), 200));
        }
        if(paramRequest.getLoadCapacityLess() != null){
            booleanBuilder.and(QCarEntity.carEntity.loadCapacity.between(0, paramRequest.getLoadCapacityLess()));
        }
        if(paramRequest.getLoadCapacityMore() != null){
            booleanBuilder.and(QCarEntity.carEntity.loadCapacity.between(paramRequest.getLoadCapacityMore(), 30000));
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
