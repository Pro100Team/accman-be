package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
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

    @Mapping(target = "walletName", source = "wallet.name")
    @Mapping(target = "transactionType", source = "typeOf")
    @Mapping(target = "amount",
            expression = "java(AmountConvertorUtils.intToStringDouble(transaction.getAmount()))")
    @Mapping(target = "subcategory", source = "transaction.subcategory.subcategoryId.name")
    @Mapping(target = "category.name", source = "transaction.category.categoryId.name")
    TransactionResponseDto transactionToResponseDto(Transaction transaction);

    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "typeOf", source = "transactionRequestDto.transactionType")
    @Mapping(target = "category", source = "profileCategory")
    @Mapping(target = "subcategory", source = "profileSubcategory")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount",
            expression = "java(AmountConvertorUtils"
                    + ".stringDoubleToInt(transactionRequestDto.getAmount()))")
    Transaction requestDtoToTransaction(
            TransactionRequestDto transactionRequestDto, ProfileSubcategory profileSubcategory,
            ProfileCategory profileCategory);

    List<TransactionResponseDto> toDtoList(
            List<Transaction> transactions);

}
