package com.innowise.cargo_transportation.web;


import com.innowise.cargo_transportation.core.configuration.CoreConfiguration;
import com.innowise.cargo_transportation.core.configuration.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CoreConfiguration.class, PersistenceConfiguration.class})
public class CargoTransportationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoTransportationApplication.class, args);
    }

}
