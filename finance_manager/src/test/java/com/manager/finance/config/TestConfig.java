package com.manager.finance.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "com.manager.finance" })
@RequiredArgsConstructor
public class TestConfig {
}
