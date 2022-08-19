package com.manager.finance.config;

import com.manager.finance.user.service.ProfileServiceImpl;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.wallet.dao.WalletDao;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(value = {"com.manager.finance"})
@RequiredArgsConstructor
@Profile("test")
public class TestConfig {

}
