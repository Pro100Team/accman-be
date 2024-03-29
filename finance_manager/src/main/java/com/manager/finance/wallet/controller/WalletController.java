package com.manager.finance.wallet.controller;

import com.manager.finance.mapstruct.mapper.WalletMapper;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import com.sandbox.api.WalletsApi;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class WalletController implements WalletsApi {
    private final WalletMapper mapper;
    private final WalletService walletService;

    @Override
    public ResponseEntity<Long> createWallet(@Valid WalletRequestDto walletRequestDto) {
        Long save = walletService.save(mapper.walletRequestDtoToWallet(walletRequestDto));
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteWalletById(Long walletId) {
        walletService.delete(walletId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        List<Wallet> walletList = walletService.getAll();
        return new ResponseEntity<>(
                mapper.walletListToWalletResponseDtoList(walletList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WalletResponseDto> getWalletById(Long walletId) {
        Wallet wallet = walletService.getByIdWithUserHolder(walletId);
        return new ResponseEntity<>(mapper.walletToWalletResponseDto(wallet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WalletResponseDto> updateWalletById(
            Long walletId, @Valid WalletRequestDto walletRequestDto) {
        Wallet wallet = walletService.getByIdWithUserHolder(walletId);
        wallet = mapper.walletRequestDtoToWalletUpdate(wallet, walletRequestDto);
        wallet.setId(walletId);
        wallet = walletService.update(wallet);
        return new ResponseEntity<>(mapper.walletToWalletResponseDto(wallet), HttpStatus.OK);
    }

}
