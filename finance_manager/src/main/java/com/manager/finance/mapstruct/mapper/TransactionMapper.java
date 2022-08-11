package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import com.sandbox.model.CreateTransactionRequest;
import com.sandbox.model.TransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "date", source = "lastUpdated")
    TransactionResponseDto transactionToDto(Transaction transaction);

    Transaction dtoToTransaction(CreateTransactionRequest createTransactionRequest);

}
