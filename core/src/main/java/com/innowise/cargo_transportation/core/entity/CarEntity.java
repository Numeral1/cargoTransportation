package com.innowise.cargo_transportation.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private int fuelConsumption;
    private int loadCapacity;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;
    @Column(name = "client_id")
    private Long clientId;

}