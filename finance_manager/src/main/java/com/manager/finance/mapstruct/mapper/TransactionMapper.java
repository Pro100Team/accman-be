package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "date", source = "lastUpdated")
    @Mapping(target = "walletName", source = "wallet.name")
    TransactionResponseDto toDto(Transaction transaction);

    @Mapping(target = "lastUpdated", source = "date")
    @Mapping(target = "isDeleted", constant = "false")
    Transaction toEntity(TransactionRequestDto transactionRequestDto);

    @Mapping(target = "date", source = "lastUpdated")
    @Mapping(target = "walletName", source = "wallet.name")
    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);

}
