package com.exadel.finance.manager.config;

import static com.exadel.finance.manager.model.UserRoleName.ADMIN;
import static com.exadel.finance.manager.model.UserRoleName.USER;

import com.exadel.finance.manager.config.currency.list.Currency;
import com.exadel.finance.manager.model.Role;
import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import com.exadel.finance.manager.service.RoleService;
import com.exadel.finance.manager.service.UserService;
import com.exadel.finance.manager.service.WalletService;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;
    private final WalletService walletService;

    @PostConstruct
    public void injectUsers() {
        List<Role> roles = roleService.findAll();

        if (roles.isEmpty()) {
            roleService.saveOrUpdate(new Role(ADMIN));
            roleService.saveOrUpdate(new Role(USER));
            roles = roleService.findAll();
            log.debug(String.format("Roles injected: %s", roles.toString()));
        }

        if (userService.findAll().isEmpty()) {
            User bobAdmin = new User();
            bobAdmin.setRoles(new HashSet<>(roles));
            bobAdmin.setEmail("bob@i.ua");
            bobAdmin.setPassword("1234");
            bobAdmin.setName("bob");
            bobAdmin = userService.saveOrUpdate(bobAdmin);

            Wallet bobWallet1 = new Wallet(bobAdmin, "cash",
                    Currency.EUR, BigDecimal.valueOf(124.3345), true);
            Wallet bobWallet2 = new Wallet(bobAdmin, "Credit Card",
                    Currency.USD, BigDecimal.valueOf(888.45), false);
            walletService.saveOrUpdate(bobWallet1);
            walletService.saveOrUpdate(bobWallet2);

            User aliceUser = new User();
            aliceUser.setRoles(Set.of(roles.get(1)));
            aliceUser.setEmail("alice@i.ua");
            aliceUser.setPassword("1234");
            aliceUser.setName("alice");
            aliceUser = userService.saveOrUpdate(aliceUser);

            Wallet aliceWallet = new Wallet(aliceUser, "cash",
                    Currency.AED, BigDecimal.valueOf(999.33), true);
            walletService.saveOrUpdate(aliceWallet);

            User thirdUserNoWallet = new User();
            thirdUserNoWallet.setRoles(Set.of(roles.get(1)));
            thirdUserNoWallet.setEmail("another@i.ua");
            thirdUserNoWallet.setPassword("1234");
            thirdUserNoWallet.setName("another");
            userService.saveOrUpdate(thirdUserNoWallet);

            log.debug("User and wallets have been injected");
        }
    }
}
