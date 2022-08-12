package com.manager.finance.mapstruct.mapper;

import com.manager.finance.wallet.model.entity.Wallet;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);

    Wallet walletRequestDtoToWalletUpdate(@MappingTarget Wallet wallet,
                                          WalletRequestDto walletRequestDto);

    Wallet walletRequestDtoToWallet(WalletRequestDto walletRequestDto);

    List<WalletResponseDto> walletListToWalletResponseDtoList(List<Wallet> wallets);
}
