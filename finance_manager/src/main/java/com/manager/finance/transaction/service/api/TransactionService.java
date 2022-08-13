/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:21
 **/

package com.manager.finance.transaction.service.api;

import java.util.List;
import org.example.model.TransactionRequestDto;
import org.example.model.TransactionResponseDto;

public interface TransactionService {
    List<TransactionResponseDto> getAll();

    List<TransactionResponseDto> findAllByWallet(Long walletId, Integer pageNumber,
                                                 Integer pageSize, String sortBy);

    TransactionResponseDto getById(Long id);

    TransactionResponseDto update(Long transactionId, TransactionRequestDto transactionDto);

    void delete(Long id);

    Long save(TransactionRequestDto transactionRequestDto);
}
