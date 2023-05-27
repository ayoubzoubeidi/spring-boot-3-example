package com.maz.springboot3example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class SpringBoot3ExampleApplicationTests {
    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer postgreSQLContainer() {
        return (PostgreSQLContainer) new PostgreSQLContainer("postgres:15.1-alpine")
                .withReuse(true);
    }

    public static void main(String[] args) {
        SpringApplication
                .from(SpringBoot3ExampleApplication::main)
                .with(SpringBoot3ExampleApplicationTests.class)
                .run(args);
    }
}
