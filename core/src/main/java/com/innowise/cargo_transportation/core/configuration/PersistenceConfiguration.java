package com.innowise.cargo_transportation.core.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.innowise.cargo_transportation.core.repository")
@EntityScan("com.innowise.cargo_transportation.core.entity")
public class PersistenceConfiguration {
}
