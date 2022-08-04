package com.exadel.finance.manager.wallet.service;

import com.exadel.finance.manager.wallet.dao.WalletDao;
import com.exadel.finance.manager.wallet.exceptions.WalletNotFoundException;
import com.exadel.finance.manager.wallet.model.entity.User;
import com.exadel.finance.manager.wallet.model.entity.Wallet;
import com.exadel.finance.manager.wallet.service.api.WalletService;
import com.sandbox.model.WalletRequestDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletDao walletDao;

    public WalletServiceImpl(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public List<Wallet> getAll() {
        return walletDao.findWalletByUserId(1L).orElseThrow(() ->
                new WalletNotFoundException("Wallets with id: " + 1L + "  Not found "));
    }

    @Override
    public Wallet get(Long id) {
        return walletDao.findById(id).orElseThrow(() ->
                new WalletNotFoundException("wallet with id: " + id + " - not found"));
    }

    @Override
    public Long save(Wallet wallet) {
        User user = new User();
        user.setId(1L);
        wallet.setUsedAt(LocalDateTime.now());
        wallet.setIsDeleted(false);
        Long id = walletDao.save(wallet).getId();
        return id;
    }

    @Override
    public Wallet update(Wallet wallet, Long id) {
        wallet.setId(id);
        walletDao.save(wallet);
        return wallet;
    }

    @Override
    public void delete(Long id) {
    }

}
