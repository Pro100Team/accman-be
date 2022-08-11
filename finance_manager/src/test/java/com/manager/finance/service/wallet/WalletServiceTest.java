package com.manager.finance.service.wallet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.dao.WalletDao;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
import com.manager.finance.wallet.service.api.WalletService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WalletServiceTest {

    private static final Wallet wallet;
    private static List<Wallet> wallets;

    static {
        wallets = new ArrayList<>();
        wallet = new Wallet();
        wallet.setName("test");
        wallet.setCurrency(DefaultCurrency.EUR);
        wallet.setIsDefault(true);
    }

    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletDao walletDao;


    @BeforeAll
    public static void createContext() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("Sergey@exadel.com", null,
                        null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test()
    @Order(1)
    public void createWallet() {
        Wallet testWallet = new Wallet();
        testWallet.setName(wallet.getName());
        testWallet.setIsDefault(wallet.getIsDefault());
        testWallet.setCurrency(wallet.getCurrency());
        Long save = walletService.save(wallet);
        assertAll(
                () -> assertNotNull(save, "Testing the creation of a new wallet"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> walletService.save(testWallet),
                        "Name and Currency Validation Check")
        );
    }

    @Test()
    @Order(2)
    public void getWalletById() {
        Wallet byIdWithUserHolder = walletService.getByIdWithUserHolder(wallet.getId());
        assertEquals(byIdWithUserHolder, wallet);
    }

    @Test()
    @Order(3)
    public void getAllWallets() {
        Wallet wallet1 = new Wallet();
        wallet1.setIsDefault(false);
        wallet1.setName("test2");
        wallet1.setCurrency(DefaultCurrency.EUR);
        wallet1.setIsDeleted(false);
        LocalDateTime usedAtTest2 = TimeZoneUtils.getGmtCurrentDate();
        wallet1.setUsedAt(usedAtTest2);
        wallet1.setProfileId(wallet.getProfileId());
        Wallet wallet2 = new Wallet();
        wallet2.setIsDefault(false);
        wallet2.setName("test3");
        wallet2.setCurrency(DefaultCurrency.EUR);
        wallet2.setIsDeleted(false);
        LocalDateTime usedAtTest3 = TimeZoneUtils.getGmtCurrentDate().plusMinutes(2L);
        wallet2.setUsedAt(usedAtTest3);
        wallet2.setProfileId(wallet.getProfileId());
        wallets.add(wallet1);
        wallets.add(wallet2);
        walletDao.saveAll(wallets);
        List<Wallet> all = walletService.getAll();
        assertAll(
                () -> assertTrue(all.get(0).getIsDefault()),
                () -> assertTrue(all.get(1).getUsedAt().isAfter(all.get(2).getUsedAt()))
        );
        wallets.add(wallet);
        walletDao.deleteAll(wallets);
    }


}
