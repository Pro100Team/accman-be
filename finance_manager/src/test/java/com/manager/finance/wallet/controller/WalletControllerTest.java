package com.manager.finance.wallet.controller;

import com.manager.finance.config.AbstractTest;
import com.manager.finance.mapstruct.mapper.WalletMapper;
import com.manager.finance.wallet.service.api.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = WalletController.class)
public class WalletControllerTest extends AbstractTest {

    @MockBean
    private WalletService walletService;

    @MockBean
    private WalletMapper walletMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createWallet() {

    }
}
