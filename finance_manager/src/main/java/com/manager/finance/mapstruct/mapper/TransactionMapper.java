package com.manager.finance.mapstruct.mapper;

import java.util.List;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.wallet.model.entity.Wallet;
import com.sandbox.model.WalletResponseDto;
import org.example.model.TransactionRequestDto;
import org.example.model.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "date", source = "lastUpdated")
    TransactionResponseDto toDto(Transaction transaction);

    @Mapping(target = "lastUpdated", source = "date")
    Transaction toEntity(TransactionRequestDto transactionRequestDto);

    @Mapping(target = "date", source = "lastUpdated")
    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);

}
