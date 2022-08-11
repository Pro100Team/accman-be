/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:21
 **/

package com.manager.finance.transaction.service.api;

import com.manager.finance.transaction.model.entity.Transaction;
import com.sandbox.model.CreateTransactionRequest;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAll();

    Transaction getById(Long id);

    Transaction update(Transaction transaction);

    void delete(Long id);

    Long save(CreateTransactionRequest createTransactionRequest);
}
