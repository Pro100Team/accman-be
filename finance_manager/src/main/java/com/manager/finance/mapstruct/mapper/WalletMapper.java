package com.manager.finance.mapstruct.mapper;

import com.manager.finance.util.AmountConvertorUtils;
import com.manager.finance.wallet.model.entity.Wallet;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = { AmountConvertorUtils.class })
public interface WalletMapper {

    @Mapping(target = "balance",
            expression = "java(AmountConvertorUtils.intToStringDouble(wallet.getAmount()))")
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);

    Wallet walletRequestDtoToWalletUpdate(@MappingTarget Wallet wallet,
                                          WalletRequestDto walletRequestDto);

    Wallet walletRequestDtoToWallet(WalletRequestDto walletRequestDto);

    @Mapping(target = "balance", source = "amount", qualifiedByName = "intToStringDouble")
    List<WalletResponseDto> walletListToWalletResponseDtoList(List<Wallet> wallets);
}
