package com.manager.finance.wallet.service;

import com.manager.finance.exception.wallet.WalletNotFoundException;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.ProfileServiceImpl;
import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.dao.WalletDao;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final ProfileServiceImpl profileService;
    private final WalletDao walletDao;

    @Override
    public List<Wallet> getAll() {
        Profile profile = profileService.findByUserIdWithValidation();
        return walletDao.findWalletByProfileIdAndIsDeleted(profile, false,
                orderByDefaultAndUsedAt());
    }

    @Override
    public Wallet getByIdWithUserHolder(Long id) {
        Profile profile = profileService.findByUserIdWithValidation();
        Wallet wallet = walletDao.findWalletByIdAndProfileIdAndIsDeleted(
                id, profile, false).orElseThrow(() ->
                new WalletNotFoundException("wallet with id: " + id + " - not found"));
        return wallet;
    }

    @Override
    public Long save(Wallet wallet) {
        Profile profile = profileService.findByUserIdWithValidation();
        currencyNameValidation(wallet, profile);
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet, profile);
        }
        wallet.setUsedAt(TimeZoneUtils.getGmtCurrentDate());
        wallet.setIsDeleted(false);
        wallet.setProfileId(profile);
        wallet.setAmount(0);
        return walletDao.save(wallet).getId();
    }

    @Override
    public Wallet update(Wallet wallet) {
        Profile profile = profileService.findByUserIdWithValidation();
        currencyNameValidation(wallet, profile);
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet, profile);
        }
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
        walletDao.save(wallet);
    }

    @Override
    public Wallet getDefaultWallet(Profile profile) {
        return walletDao.findWalletByIsDefaultAndProfileId(true, profile);
    }

    private void switchDefaultWallet(Wallet wallet, Profile profile) {
        Wallet defaultWallet = getDefaultWallet(profile);
        if (defaultWallet != null) {
            if (!defaultWallet.getId().equals(wallet.getId())) {
                defaultWallet.setIsDefault(false);
                walletDao.save(defaultWallet);
            }
        }
    }

    private void currencyNameValidation(Wallet wallet, Profile profile) {
        List<Wallet> dbWallet =
                walletDao.findWalletByNameAndCurrencyAndProfileId(wallet.getName(),
                        wallet.getCurrency(), profile);
        if (dbWallet != null && dbWallet.size() > 0) {
            if (dbWallet.size() == 1 && dbWallet.get(0).getId().equals(wallet.getId())) {
                return;
            }
            throw new IllegalArgumentException(
                    "You cannot have wallets with the same name and currency");
        }
    }

    private Sort orderByDefaultAndUsedAt() {
        return Sort.by(Sort.Direction.DESC, "isDefault")
                .and(Sort.by(Sort.Direction.DESC, "usedAt"));
    }
}
