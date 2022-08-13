package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import org.example.model.TransactionRequestDto;
import org.example.model.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "date", source = "lastUpdated")
    TransactionResponseDto transactionToDto(Transaction transaction);

    @Mapping(target = "lastUpdated", source = "date")
    Transaction dtoToTransaction(TransactionRequestDto transactionRequestDto);

}
