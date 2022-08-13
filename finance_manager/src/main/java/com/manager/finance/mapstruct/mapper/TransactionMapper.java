package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import java.util.List;
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
