package com.exadel.finance.manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.exadel.finance.manager")
public class MapperConfig {

    @Bean
    public ModelMapper getEntityMapper() {
        return new ModelMapper();
    }
}
