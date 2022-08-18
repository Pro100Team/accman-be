package com.manager.finance.config;

import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import com.manager.finance.wallet.dao.WalletDao;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(value = {"com.manager.finance"})
@RequiredArgsConstructor
public class TestConfig {
//    @Bean
//    @Primary
//    public ProfileService profileService() {
//        return Mockito.mock(ProfileService.class);
//    }

//    @Bean
//    @Primary
//    public UserService userService() {
//        return Mockito.mock(UserService.class);
//    }

//    @Bean
//    @Primary
//    public WalletDao walletDao() {
//        return Mockito.mock(WalletDao.class);
//    }
}
