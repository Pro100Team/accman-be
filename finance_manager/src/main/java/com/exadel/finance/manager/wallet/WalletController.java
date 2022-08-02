package com.exadel.finance.manager.wallet;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletRepository repository;

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWalltes() {
        return ResponseEntity.ok(repository.findAll());
    }

   @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable(name = "id") Long id) {
        Wallet wallet = new Wallet();
        wallet.setName("NO SUCH WALLET");
        return ResponseEntity.ok(repository.findById(id).orElse(wallet));
    }

    @PutMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(repository.save(wallet));
    }
}
