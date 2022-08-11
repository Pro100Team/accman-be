/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:27
 **/

package com.manager.finance.transaction.service;

import com.manager.finance.mapstruct.mapper.TransactionMapper;
import com.manager.finance.transaction.dao.TransactoionDao;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.transaction.service.api.TransactionService;
import com.sandbox.model.CreateTransactionRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactoionDao transactoionDao;
    private final TransactionMapper transactionMapper;

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Transaction getById(Long id) {
        return null;
    }

    @Override
    public Transaction update(Transaction transaction) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long save(CreateTransactionRequest createTransactionRequest) {

        return 1L;

    }
}
