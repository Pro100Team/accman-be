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

    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> getWalletsBySpecification(
            @RequestParam(value = "search") String search) {
        return ResponseEntity.ok(mapToList(walletService.findAll(search), WalletResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable("id") Long walletId) {
        return ResponseEntity.ok(map(walletService.findById(walletId), WalletResponseDto.class));
    }

    @GetMapping("/personal")
    public ResponseEntity<List<WalletResponseDto>> getAllUserWallets(
            @RequestParam(name = "accountId") Long userId) {
        return ResponseEntity.ok(mapToList(walletService
                .findAllByUser(userService.findById(userId)), WalletResponseDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWalletById(@PathVariable("id") Long walletId) {
        walletService.deleteById(walletId);
        return ResponseEntity.ok("Wallet has been deleted successfully");
    }

    @PutMapping
    public ResponseEntity<WalletResponseDto> saveOrUpdateWallet(
            @RequestParam(name = "accountId") Long userId,
            @RequestBody @Valid WalletRequestDto walletRequestDto) {
        Wallet wallet = map(walletRequestDto, Wallet.class);
        wallet.setUser(userService.findById(userId));
        return ResponseEntity.ok(map(walletService.saveOrUpdate(wallet), WalletResponseDto.class));
    }
}
