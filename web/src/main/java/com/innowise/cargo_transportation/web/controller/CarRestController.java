package com.innowise.cargo_transportation.web.controller;

import com.innowise.cargo_transportation.core.dto.request.CarParamRequest;
import com.innowise.cargo_transportation.core.dto.request.CarRequest;
import com.innowise.cargo_transportation.core.dto.response.CarListResponse;
import com.innowise.cargo_transportation.core.dto.response.CarResponse;
import com.innowise.cargo_transportation.core.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarRestController {
    private final CarService service;

    @PostMapping
    public ResponseEntity<URI> create(@RequestBody CarRequest carRequest) {
     Long id = service.createCar(carRequest);
     URI location = ServletUriComponentsBuilder.fromCurrentRequest()
             .path("/{id}")
             .buildAndExpand(id).toUri();
        return ResponseEntity.created(location)
                .body(location);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody CarRequest carRequest){
        service.updateCar(id, carRequest);
    }

    @GetMapping("/{id}")
    public CarResponse getById(@PathVariable("id") long id){
        return service.findCarById(id);
    }

    @GetMapping
    public CarListResponse getByFilter(CarParamRequest carParamRequest){
        return service.findList(carParamRequest);
    }

    @DeleteMapping
    public void delete(List<Long> id){
        service.deleteCar(id);
    }
}
