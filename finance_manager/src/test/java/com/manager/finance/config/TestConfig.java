package com.manager.finance.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(value = {"com.manager.finance"})
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

}
