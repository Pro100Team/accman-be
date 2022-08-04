package com.exadel.finance.manager.mapstruct.mappers;

import com.exadel.finance.manager.wallet.model.entity.Wallet;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    @Mapping(target = "_default", source = "isDefault")
    WalletRequestDto walletToWalletRequestDto(Wallet wallet);

    @Mapping(target = "_default", source = "isDefault")
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);

    @Mapping(target = "isDefault", source = "default")
    Wallet walletResponseDtoToWallet(WalletResponseDto walletResponseDto);

    @Mapping(target = "isDefault", source = "default")
    Wallet walletRequestDtoToWallet(WalletRequestDto walletRequestDto);

    List<WalletResponseDto> walletListToWalletResponseDtoList(List<Wallet> wallets);
}