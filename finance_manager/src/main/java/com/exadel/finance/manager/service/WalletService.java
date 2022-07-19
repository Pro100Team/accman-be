package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import java.util.List;

public interface WalletService extends GenericService<Wallet> {
    List<Wallet> findAllByUser(User user);
}
