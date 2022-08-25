package com.manager.finance.wallet.controller;

import com.manager.finance.mapstruct.mapper.WalletMapper;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.api.ProfileService;
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
    private final ProfileService profileService;

    @Override
    public ResponseEntity<Long> createWallet(@Valid WalletRequestDto walletRequestDto) {
        Profile profile = profileService.findByUserIdOrCreate();
        Long save = walletService.save(mapper.walletRequestDtoToWallet(walletRequestDto), profile);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteWalletById(Long walletId) {
        Profile profile = profileService.findByUserIdOrCreate();
        walletService.delete(walletId, profile);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        Profile profile = profileService.findByUserIdOrCreate();
        List<Wallet> walletList = walletService.getAll(profile);
        return new ResponseEntity<>(
                mapper.walletListToWalletResponseDtoList(walletList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WalletResponseDto> getWalletById(Long walletId) {
        Profile profile = profileService.findByUserIdOrCreate();
        Wallet wallet = walletService.getById(walletId, profile);
        return new ResponseEntity<>(mapper.walletToWalletResponseDto(wallet), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WalletResponseDto> updateWalletById(
            Long walletId, @Valid WalletRequestDto walletRequestDto) {
        Profile profile = profileService.findByUserIdOrCreate();
        Wallet wallet = walletService.getById(walletId, profile);
        wallet = mapper.walletRequestDtoToWalletUpdate(wallet, walletRequestDto);
        wallet.setId(walletId);
        wallet = walletService.update(wallet, profile);
        return new ResponseEntity<>(mapper.walletToWalletResponseDto(wallet), HttpStatus.OK);
    }

}
