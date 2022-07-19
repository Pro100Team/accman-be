package com.exadel.finance.manager.service.funds;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import com.exadel.finance.manager.repository.WalletRepository;
import com.exadel.finance.manager.service.AbstractEntityService;
import com.exadel.finance.manager.service.WalletService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class WalletServiceImpl extends AbstractEntityService<Wallet> implements WalletService {
    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        super(walletRepository);
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> findAllByUser(User user) {
        log.debug("findAllByUser() is called for {}", user.toString());
        return walletRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(Long walletId) {
        if (this.findById(walletId).getIsDefault()) {
            throw new IllegalStateException("Unable to delete default wallet. "
                    + "Please select another wallet as default before deletion");
        } else {
            super.deleteById(walletId);
        }
    }

    @Override
    public Wallet saveOrUpdate(Wallet wallet) {
        if (this.isUniqueUserWalletName(wallet)) {
            return super.saveOrUpdate(this.selectUniqueDefaultWallet(wallet));
        } else {
            throw new IllegalStateException("Wallet with such name already exists");
        }
    }

    private boolean isUniqueUserWalletName(Wallet createdWallet) {
        return this.findAllByUser(createdWallet.getUser())
                .stream()
                .noneMatch(userWallet -> userWallet.getName().equals(createdWallet.getName()));
    }

    private Wallet selectUniqueDefaultWallet(Wallet createdWallet) {
        List<Wallet> userWallets = this.findAllByUser(createdWallet.getUser());
        if (userWallets.isEmpty()) {
            setFirstUserWalletAsDefault(createdWallet);
        } else if (createdWallet.getIsDefault()) {
            setFalsePreviousDefaultWallet(userWallets);
        }
        return createdWallet;
    }

    private void setFalsePreviousDefaultWallet(List<Wallet> userWallets) {
        Wallet previousDefaultWallet = userWallets.stream()
                .filter(Wallet::getIsDefault)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unable to find default wallet"));
        previousDefaultWallet.setIsDefault(false);
        this.saveOrUpdate(previousDefaultWallet);
    }

    private void setFirstUserWalletAsDefault(Wallet wallet) {
        wallet.setIsDefault(true);
    }
}
