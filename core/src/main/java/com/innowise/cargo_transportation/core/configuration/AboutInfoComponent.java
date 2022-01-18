package com.innowise.cargo_transportation.core.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:about.properties")
@Component
@Getter
public class AboutInfoComponent {
    @Value("${applicationName}")
    private String applicationName;
    @Value("${version}")
    private String version;
}
