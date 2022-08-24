/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:21
 **/

package com.manager.finance.transaction.service.api;

import com.sandbox.model.SortParameter;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import java.util.List;

public interface TransactionService {
    List<TransactionResponseDto> getAll(Long pageNumber, Long pageSize);

    List<TransactionResponseDto> findAllByWallet(Long walletId, Long pageNumber,
                                                 Long pageSize, SortParameter sortBy);

    TransactionResponseDto getById(Long id);

    TransactionResponseDto update(Long transactionId, TransactionRequestDto transactionDto);

    void delete(Long id);

    Long save(TransactionRequestDto transactionRequestDto);
}
