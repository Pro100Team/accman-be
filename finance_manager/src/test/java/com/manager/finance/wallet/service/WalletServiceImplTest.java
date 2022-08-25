package com.manager.finance.wallet.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Country;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.ProfileServiceImpl;
import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.dao.WalletDao;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {
    @Mock
    private WalletDao walletDao;
    @Mock
    private ProfileServiceImpl profileService;
    @InjectMocks
    private WalletServiceImpl walletService;

    @Test()
    public void createWallet() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(1L)
                .isDeleted(false).name("testName").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .build();
        Mockito.when(profileService.findByUserId()).thenReturn(profile);
        Mockito.when(walletDao.save(wallet)).thenReturn(wallet);
        Long walletId = walletService.save(wallet);
        assertEquals(wallet.getId(), walletId);
    }

    @Test()
    public void getWalletById() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(1L)
                .isDeleted(false).name("testName").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .build();

        Mockito.when(profileService.findByUserId()).thenReturn(profile);

        Mockito.when(walletDao.findWalletByIdAndProfileIdAndIsDeleted(1L, profile, false))
                .thenReturn(Optional.of(wallet));

        Wallet byIdWithUserHolder = walletService.getByIdWithUserHolder(1L);
        assertEquals(wallet, byIdWithUserHolder);
    }

    @Test()
    public void getAllWallets() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet defaultWallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(3L)
                .isDeleted(false).name("testName3").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .profileId(profile)
                .build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(false).id(2L)
                .isDeleted(false).name("testName2")
                .usedAt(TimeZoneUtils.getGmtCurrentDate().plusMinutes(3))
                .profileId(profile)
                .build();
        Wallet oldWallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(false).id(1L)
                .isDeleted(false).name("testName1")
                .usedAt(TimeZoneUtils.getGmtCurrentDate())
                .profileId(profile)
                .build();
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(defaultWallet);
        wallets.add(wallet);
        wallets.add(oldWallet);

        Sort sort = Sort.by(Sort.Direction.DESC, "isDefault")
                .and(Sort.by(Sort.Direction.DESC, "usedAt"));

        Mockito.when(profileService.findByUserId()).thenReturn(profile);
        Mockito.when(walletDao.findWalletByProfileIdAndIsDeleted(profile, false, sort))
                .thenReturn(wallets);
        List<Wallet> all = walletService.getAll();

        assertAll(
                () -> assertTrue(all.get(0).getIsDefault()),
                () -> assertTrue(all.get(1).getUsedAt().isAfter(all.get(2).getUsedAt()))
        );
    }

    @Test
    public void updateWallet() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(1L)
                .isDeleted(false).name("testName").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .build();
        Mockito.when(profileService.findByUserId()).thenReturn(profile);
        Mockito.when(walletDao.save(wallet)).thenReturn(wallet);
        Wallet walletId = walletService.update(wallet);
        assertEquals(wallet, walletId);
    }

    @Test
    public void deleteWallet() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Wallet wallet = Wallet.builder().currency(DefaultCurrency.EUR).isDefault(true).id(1L)
                .isDeleted(false).name("testName").usedAt(TimeZoneUtils.getGmtCurrentDate())
                .build();
        Mockito.when(walletDao.findWalletByIdAndProfileIdAndIsDeleted(1L, profile, false))
                .thenReturn(Optional.of(wallet));
        Mockito.when(profileService.findByUserId()).thenReturn(profile);

        assertThrows(IllegalArgumentException.class, () -> walletService.delete(wallet.getId()));
        wallet.setIsDefault(false);
        walletService.delete(wallet.getId());
    }

}
