package com.exadel.managger.finance.wallet.service;

import com.exadel.managger.finance.exception.wallet.WalletNotFoundException;
import com.exadel.managger.finance.user.model.entity.Profile;
import com.exadel.managger.finance.user.service.ProfileServiceImpl;
import com.exadel.managger.finance.user.service.api.UserService;
import com.exadel.managger.finance.util.TimeZoneUtils;
import com.exadel.managger.finance.wallet.dao.WalletDao;
import com.exadel.managger.finance.wallet.model.entity.Wallet;
import com.exadel.managger.finance.wallet.service.api.WalletService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final UserService userService;
    private final ProfileServiceImpl profileService;
    private final WalletDao walletDao;

    @Override
    public List<Wallet> getAll() {
        Profile profile = profileService.findByUserId(userService.getByUserHolder());
        return walletDao.findWalletByProfileId(profile);
    }

    @Override
    public Wallet getByIdWithUserHolder(Long id) {
        return walletDao.findWalletByIdAndProfileId(
                        id, profileService.findByUserId(userService.getByUserHolder()))
                .orElseThrow(()
                        -> new WalletNotFoundException("wallet with id: \" + id + \" - not found"));
    }

    @Override
    public Long save(Wallet wallet) {
        currencyNameValidation(wallet);
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet);
        }
        wallet.setUsedAt(TimeZoneUtils.getGmtCurrentDate());
        wallet.setIsDeleted(false);
        wallet.setProfileId(profileService.findByUserId(userService.getByUserHolder()));
        return walletDao.save(wallet).getId();
    }

    @Override
    public Wallet update(Wallet wallet, Long id) {
        currencyNameValidation(wallet);
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet);
        }
        wallet.setId(id);
        wallet.setUsedAt(LocalDateTime.now());
        walletDao.save(wallet);
        return wallet;
    }

    @Override
    public void delete(Long id) {
        Wallet wallet = getByIdWithUserHolder(id);
        if (wallet.getIsDefault()) {
            throw new IllegalArgumentException("Unable to delete default wallet");
        }
        wallet.setIsDeleted(true);
        save(wallet);
    }

    private void switchDefaultWallet(Wallet wallet) {
        Wallet defaultWallet = walletDao.findWalletByIsDefaultAndProfileId(true,
                profileService.findByUserId(userService.getByUserHolder()));
        if (defaultWallet != null) {
            if (!defaultWallet.getId().equals(wallet.getId())) {
                defaultWallet.setIsDefault(false);
                walletDao.save(defaultWallet);
            }
        }
    }

    private void currencyNameValidation(Wallet wallet) {
        List<Wallet> byNameAndCurrency =
                walletDao.findWalletByNameAndCurrency(wallet.getName(), wallet.getCurrency());
        if (byNameAndCurrency != null && byNameAndCurrency.size() > 0) {
            throw new IllegalArgumentException(
                    "You cannot have wallets with the same name and currency");
        }
    }

}
