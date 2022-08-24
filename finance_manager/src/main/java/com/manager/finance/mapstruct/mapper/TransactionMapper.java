package com.manager.finance.mapstruct.mapper;

import com.manager.finance.transaction.model.entity.Transaction;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.Formatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})

public interface TransactionMapper {

    @Named("stringDoubleToInt")
    public static Integer stringDoubleToIntSource(String amount) {
        return (int) Math.round(Double.parseDouble(amount) * 100);
    }

    @Named("intToStringDouble")
    public static String intToStringDouble(Integer amount) {
        return amount == null ? "0.00"
                : new Formatter().format("%.2f", ((double) (amount)) / 100).toString();
    }

    @Named("createCategoryByName")
    public static CategoryResponseDto stringDoubleToInt(String name) {
        CategoryResponseDto category = new CategoryResponseDto();
        category.setId(1L);
        category.setCategoryType(TransactionTypeParameter.EXPENSE);
        category.setName(name);
        category.setColor("11111111");
        return category;
    }

    @Mapping(target = "walletName", source = "wallet.name")
    @Mapping(target = "transactionType", source = "typeOf")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "intToStringDouble")
    TransactionResponseDto toDto(Transaction transaction);

    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "typeOf", source = "transactionType")
    @Mapping(target = "amount", source = "amount", qualifiedByName = "stringDoubleToInt")
    Transaction toEntity(TransactionRequestDto transactionRequestDto);

    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);

}
