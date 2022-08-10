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
    @Mapping(target = "_default", source = "isDefault")
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);

    @Mapping(target = "isDefault", source = "default")
    Wallet walletRequestDtoToWalletUpdate(@MappingTarget Wallet wallet,
                                          WalletRequestDto walletRequestDto);
    @Mapping(target = "isDefault", source = "default")
    Wallet walletRequestDtoToWallet(WalletRequestDto walletRequestDto);

    List<WalletResponseDto> walletListToWalletResponseDtoList(List<Wallet> wallets);
}
