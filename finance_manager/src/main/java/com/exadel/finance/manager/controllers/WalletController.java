package com.exadel.finance.manager.controllers;

import static com.exadel.finance.manager.util.MapperUtil.map;
import static com.exadel.finance.manager.util.MapperUtil.mapToList;

import com.exadel.finance.manager.model.Wallet;
import com.exadel.finance.manager.model.dto.request.WalletRequestDto;
import com.exadel.finance.manager.model.dto.response.WalletResponseDto;
import com.exadel.finance.manager.service.UserService;
import com.exadel.finance.manager.service.WalletService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable("id") Long walletId) {
        return ResponseEntity.ok(map(walletService.findById(walletId), WalletResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> getAllUserWallets(
            @RequestParam(name = "accountId") Long userId) {
        return ResponseEntity.ok(mapToList(walletService
                .findAllByUser(userService.findById(userId)), WalletResponseDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWalletById(@PathVariable("id") Long walletId) {
        Wallet wallet = walletService.findById(walletId);
        if (wallet.getIsDefault()) {
            return ResponseEntity.badRequest().body("This is default wallet. "
                    + "Please mark another wallet as default before deletion.");
        }
        walletService.deleteById(walletId);
        return ResponseEntity.ok(map(wallet, WalletResponseDto.class).getName()
                + " wallet has been deleted successfully");
    }

    @PutMapping
    public ResponseEntity<WalletResponseDto> saveOrUpdateWallet(
            @RequestParam(name = "accountId") Long userId,
            @RequestBody @Valid WalletRequestDto walletRequestDto) {
        Wallet wallet = map(walletRequestDto, Wallet.class);
        wallet.setUser(userService.findById(userId));
        wallet = walletService.saveOrUpdate(walletService.setUniqueDefaultWallet(wallet));
        return ResponseEntity.ok(map(wallet, WalletResponseDto.class));
    }
}
