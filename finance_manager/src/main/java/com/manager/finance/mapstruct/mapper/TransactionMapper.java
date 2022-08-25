package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import java.util.Formatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Named("stringDoubleToInt")
    public static int intToStringDouble(String amount) {
        return (int) Math.round(Double.parseDouble(amount) * 100);
    }

    @Named("intToStringDouble")
    public static String stringDoubleToInt(int amount) {
        return new Formatter().format("%.2f",
                ((double) (amount)) / 100).toString().replace(",", ".");
    }

    @Mapping(target = "date", source = "lastUpdated")
    @Mapping(target = "walletName", source = "wallet.name")
    @Mapping(target = "transactionType", source = "typeOf")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "intToStringDouble")
    @Mapping(target = "category", source = "transaction.category.categoryId")
    @Mapping(target = "subcategory", ignore = true)
    TransactionResponseDto toDto(Transaction transaction);

    @Mapping(target = "lastUpdated", source = "date")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "typeOf", source = "transactionType")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "stringDoubleToInt")
    @Mapping(target = "subcategory", ignore = true)
    Transaction toEntity(TransactionRequestDto transactionRequestDto);

    @Mapping(target = "date", source = "lastUpdated")
    @Mapping(target = "walletName", source = "wallet.name")
    @Mapping(target = "transactionType", source = "typeOf")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "intToStringDouble")
    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);

}
