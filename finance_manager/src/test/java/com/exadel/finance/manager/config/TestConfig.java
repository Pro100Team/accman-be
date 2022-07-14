package com.exadel.finance.manager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "com.exadel.finance.manager" })
@RequiredArgsConstructor
public class TestConfig {
}
