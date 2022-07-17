package com.exadel.finance.manager.service.funds;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import com.exadel.finance.manager.repository.WalletRepository;
import com.exadel.finance.manager.service.WalletService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public Wallet saveOrUpdate(Wallet wallet) {
        log.debug("saveOrUpdate() is called for {}", wallet);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findById(Long id) {
        log.debug("findById() is called for {}", id);
        return walletRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id, "Unable to locate Wallet in database"));
    }

    @Override
    public List<Wallet> findAllByUser(User user) {
        log.debug("findAllByUser() is called for {}", user.toString());
        return walletRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById() is called for {}", id);
        walletRepository.deleteById(id);
    }

    @Override
    public Wallet setUniqueDefaultWallet(Wallet wallet) {
        List<Wallet> userWallets = this.findAllByUser(wallet.getUser());
        if (userWallets.isEmpty()) {
            wallet.setIsDefault(true);
        } else if (wallet.getIsDefault()) {
            Wallet previousDefaultWallet = userWallets.stream()
                    .filter(Wallet::getIsDefault)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to find default wallet"));
            previousDefaultWallet.setIsDefault(false);
            this.saveOrUpdate(previousDefaultWallet);
        }
        return wallet;
    }
}
