package com.manager.finance.mapstruct.mapper;

import com.manager.finance.wallet.model.entity.Wallet;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.Formatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Named("intToStringDouble")
    public static String intToStringDouble(int amount) {
        return new Formatter().format("%.2f", ((double) (amount)) / 100).toString();
    }

    @Mapping(target = "balance", source = "amount", qualifiedByName = "intToStringDouble")
    WalletResponseDto walletToWalletResponseDto(Wallet wallet);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "isDeleted",ignore = true)
    @Mapping(target = "usedAt",ignore = true)
    @Mapping(target = "profileId",ignore = true)
    @Mapping(target = "amount",ignore = true)
    Wallet walletRequestDtoToWalletUpdate(@MappingTarget Wallet wallet,
                                          WalletRequestDto walletRequestDto);

    @Mapping(target = "isDeleted",ignore = true)
    @Mapping(target = "usedAt",ignore = true)
    @Mapping(target = "profileId",ignore = true)
    @Mapping(target = "amount",ignore = true)
    @Mapping(target = "id",ignore = true)
    Wallet walletRequestDtoToWallet(WalletRequestDto walletRequestDto);

    @Mapping(target = "balance", source = "amount", qualifiedByName = "intToStringDouble")
    List<WalletResponseDto> walletListToWalletResponseDtoList(List<Wallet> wallets);
}
