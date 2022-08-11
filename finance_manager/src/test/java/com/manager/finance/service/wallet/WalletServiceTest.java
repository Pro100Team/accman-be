package com.manager.finance.service.wallet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.manager.finance.exception.wallet.WalletNotFoundException;
import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.dao.WalletDao;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
import com.manager.finance.wallet.service.api.WalletService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
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
    private static final List<Wallet> wallets;

    static {
        wallets = new ArrayList<>();
        Wallet defaultWallet = new Wallet();
        defaultWallet.setName("defaultTest");
        defaultWallet.setCurrency(DefaultCurrency.EUR);
        defaultWallet.setIsDefault(true);
        Wallet wallet = new Wallet();
        wallet.setName("test");
        wallet.setCurrency(DefaultCurrency.EUR);
        wallet.setIsDefault(false);
        Wallet thirdWallet = new Wallet();
        thirdWallet.setName("test3");
        thirdWallet.setCurrency(DefaultCurrency.EUR);
        thirdWallet.setIsDefault(false);
        wallets.add(wallet);
        wallets.add(defaultWallet);
        wallets.add(thirdWallet);

    }

    @Autowired
    private WalletService walletService;

    private static WalletDao walletDao;

    @Autowired
    public WalletServiceTest(WalletDao walletDaoDep) {
        walletDao = walletDaoDep;
    }


    @BeforeAll
    public static void createContext() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("Sergey@exadel.com", null,
                        null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterAll
    public static void cleanTestSource() {
        walletDao.deleteAll(wallets);
    }

    @Test()
    @Order(1)
    public void createWallet() {
        Wallet testWallet = new Wallet();
        testWallet.setName(wallets.get(0).getName());
        testWallet.setIsDefault(wallets.get(0).getIsDefault());
        testWallet.setCurrency(wallets.get(0).getCurrency());
        Long save = walletService.save(wallets.get(0));
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
        Wallet byIdWithUserHolder = walletService.getByIdWithUserHolder(wallets.get(0).getId());
        assertEquals(byIdWithUserHolder, wallets.get(0));
    }

    @Test()
    @Order(3)
    public void getAllWallets() {

        wallets.get(1).setIsDeleted(false);
        LocalDateTime usedAtDefaultWallet = TimeZoneUtils.getGmtCurrentDate().plusMinutes(2L);
        wallets.get(1).setUsedAt(usedAtDefaultWallet);
        wallets.get(1).setProfileId(wallets.get(0).getProfileId());

        wallets.get(2).setIsDeleted(false);
        LocalDateTime usedAtTest3 = TimeZoneUtils.getGmtCurrentDate();
        wallets.get(2).setUsedAt(usedAtTest3);
        wallets.get(2).setProfileId(wallets.get(0).getProfileId());
        walletDao.saveAll(wallets);
        List<Wallet> all = walletService.getAll();
        assertAll(
                () -> assertTrue(all.get(0).getIsDefault()),
                () -> assertTrue(all.get(1).getUsedAt().isAfter(all.get(2).getUsedAt()))
        );
    }

    @Test
    @Order(4)
    public void updateWallet() {
        Wallet update = walletService.update(wallets.get(1));
        wallets.get(1).setName("updatedTest");
        assertAll(
                () -> assertEquals(update, wallets.get(1), "Wallet update check"),
                () -> assertDoesNotThrow(() -> walletService.update(update),
                        "Validation of the name and currency of wallets with the same id")
        );
    }

    @Test
    @Order(5)
    public void deleteWallet() {
        walletService.delete(wallets.get(0).getId());
        assertThrows(WalletNotFoundException.class,
                () -> walletService.getByIdWithUserHolder(wallets.get(0).getId()));
    }

    @Test
    @Order(6)
    public void switchDefault() {

        wallets.get(2).setIsDefault(true);
        wallets.get(2).setUsedAt(TimeZoneUtils.getGmtCurrentDate());
        walletService.save(wallets.get(2));
        assertAll(
                () -> assertFalse(
                        walletService.getByIdWithUserHolder(wallets.get(1).getId()).getIsDefault()),
                () -> assertTrue(
                        walletService.getByIdWithUserHolder(wallets.get(2).getId()).getIsDefault())
        );
    }

}
