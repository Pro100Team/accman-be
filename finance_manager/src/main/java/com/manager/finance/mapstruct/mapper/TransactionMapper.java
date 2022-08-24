package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.util.AmountConvertorUtils;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class},
        imports = {AmountConvertorUtils.class})

public interface TransactionMapper {

    @Mapping(target = "date", source = "lastUpdated")
    @Mapping(target = "walletName", source = "wallet.name")
    @Mapping(target = "transactionType", source = "typeOf")
    @Mapping(target = "amount",
            expression = "java(AmountConvertorUtils.intToStringDouble(transaction.getAmount()))")
    TransactionResponseDto transactionToResponseDto(Transaction transaction);

    @Mapping(target = "lastUpdated", source = "date")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "typeOf", source = "transactionType")
    @Mapping(target = "amount",
            expression = "java(AmountConvertorUtils"
                    + ".stringDoubleToInt(transactionRequestDto.getAmount()))")
    Transaction requestDtoToTransaction(
            TransactionRequestDto transactionRequestDto);

    List<TransactionResponseDto> transactionsListToResponseDtoList(
            List<Transaction> transactions);

}
