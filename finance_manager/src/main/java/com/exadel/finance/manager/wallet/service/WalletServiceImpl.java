package com.exadel.finance.manager.wallet.service;

import com.exadel.finance.manager.utils.TimeZoneUtils;
import com.exadel.finance.manager.wallet.dao.WalletDao;
import com.exadel.finance.manager.wallet.exceptions.WalletNotFoundException;
import com.exadel.finance.manager.wallet.model.entity.User;
import com.exadel.finance.manager.wallet.model.entity.Wallet;
import com.exadel.finance.manager.wallet.service.api.WalletService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletDao walletDao;

    @Override
    public List<Wallet> getAll() {
        User user = new User();
        user.setId(1L);
        return walletDao.findWalletByUserId(user);
    }

    @Override
    public Wallet get(Long id) {
        return walletDao.findById(id).orElseThrow(() ->
                new WalletNotFoundException("wallet with id: " + id + " - not found"));
    }

    @Override
    public Long save(Wallet wallet) {
        currencyNameValidation(wallet);
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet);
        }
        User user = new User();
        user.setId(1L);
        wallet.setUsedAt(TimeZoneUtils.getGmtCurrentDate());
        wallet.setIsDeleted(false);
        return walletDao.save(wallet).getId();
    }

    @Override
    public Wallet update(Wallet wallet, Long id) {
        currencyNameValidation(wallet);
        wallet.setId(id);
        wallet.setUsedAt(LocalDateTime.now());
        if (wallet.getIsDefault()) {
            switchDefaultWallet(wallet);
        }
        walletDao.save(wallet);
        return wallet;
    }

    @Override
    public void delete(Long id) {
        Wallet wallet = get(id);
        if (wallet.getIsDefault()) {
            throw new IllegalArgumentException("Unable to delete default wallet");
        }
        wallet.setIsDeleted(true);
        save(wallet);
    }

    private void switchDefaultWallet(Wallet wallet) {
        Wallet defaultWallet = walletDao.findWalletByIsDefault(true);
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
