package com.manager.finance.wallet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.manager.finance.config.AbstractTest;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Country;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.ProfileServiceImpl;
import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.dao.WalletDao;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest extends AbstractTest {
    @Mock
    private WalletDao walletDao;
    @Mock
    private ProfileServiceImpl profileService;
    @InjectMocks
    private WalletServiceImpl walletService;

    @BeforeAll
    public static void sourceInitialize() {
    }

    @AfterAll
    public static void cleanTestSource() {

    }

    @Test()
    @Order(1)
    public void createWallet() {

//        Wallet testWallet = new Wallet();
//        testWallet.setName("test");
//        testWallet.setIsDefault(true);
//        testWallet.setCurrency(DefaultCurrency.EUR);
//        Long save = walletService.save(wallets.get(0));
//        assertAll(
//                () -> assertNotNull(save, "Testing the creation of a new wallet"),
//                () -> assertThrows(IllegalArgumentException.class,
//                        () -> walletService.save(testWallet),
//                        "Name and Currency Validation Check")
//        );
    }

    @Test()
    @Order(2)
    public void getWalletById() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(1L)
                .isDeleted(false).name("testName").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .build();

        Mockito.when(profileService.findByUserIdWithValidation()).thenReturn(profile);

        Mockito.when(walletDao.findWalletByIdAndProfileIdAndIsDeleted(1L, profile, false))
                .thenReturn(Optional.of(wallet));

        Wallet byIdWithUserHolder = walletService.getByIdWithUserHolder(1L);
        assertEquals(wallet, byIdWithUserHolder);
//    }

//    @Test()
//    @Order(3)
//    public void getAllWallets() {
//        wallets.get(1).setIsDeleted(false);
//        LocalDateTime usedAtDefaultWallet = TimeZoneUtils.getGmtCurrentDate().plusMinutes(2L);
//        wallets.get(1).setUsedAt(usedAtDefaultWallet);
//        wallets.get(1).setProfileId(wallets.get(0).getProfileId());
//        wallets.get(2).setIsDeleted(false);
//        LocalDateTime usedAtTest3 = TimeZoneUtils.getGmtCurrentDate();
//        wallets.get(2).setUsedAt(usedAtTest3);
//        wallets.get(2).setProfileId(wallets.get(0).getProfileId());
//        walletDao.saveAll(wallets);
//        List<Wallet> all = walletService.getAll();
//        assertAll(
//                () -> assertTrue(all.get(0).getIsDefault()),
//                () -> assertTrue(all.get(1).getUsedAt().isAfter(all.get(2).getUsedAt()))
//        );
//    }

//    @Test
//    @Order(4)
//    public void updateWallet() {
//        Wallet update = walletService.update(wallets.get(1));
//        wallets.get(1).setName("updatedTest");
//        assertAll(
//                () -> assertEquals(update, wallets.get(1), "Wallet update check"),
//                () -> assertDoesNotThrow(() -> walletService.update(update),
//                        "Validation of the name and currency of wallets with the same id")
//        );
//    }
//
//    @Test
//    @Order(5)
//    public void deleteWallet() {
//
//        Wallet defaultWallet = walletDao.findWalletByIsDefaultAndProfileId(true,
//                profileService.findActiveProfileByUserId());
//        walletService.delete(wallets.get(0).getId());
//        assertAll(
//                () -> assertThrows(WalletNotFoundException.class,
//                        () -> walletService.getByIdWithUserHolder(wallets.get(0).getId())),
//                () -> assertThrows(IllegalArgumentException.class,
//                        () -> walletService.delete(defaultWallet.getId())
//                )
//        );
//
//    }
//
//    @Test
//    @Order(6)
//    public void switchDefault() {
//
//        wallets.get(2).setIsDefault(true);
//        wallets.get(2).setUsedAt(TimeZoneUtils.getGmtCurrentDate());
//        walletService.save(wallets.get(2));
//        assertAll(
//                () -> assertFalse(
//                        walletService.getByIdWithUserHolder(wallets.get(1).getId()).getIsDefault()),
//                () -> assertTrue(
//                        walletService.getByIdWithUserHolder(wallets.get(2).getId()).getIsDefault())
//        );
//    }

    }
}