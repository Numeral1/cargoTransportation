package com.innowise.cargo_transportation.web;


import com.innowise.cargo_transportation.core.configuration.CoreConfiguration;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CoreConfiguration.class})
public class CargoTransportationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoTransportationApplication.class, args);
    }

}
