/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:27
 **/

package com.manager.finance.transaction.service;

import com.manager.finance.mapstruct.mapper.TransactionMapper;
import com.manager.finance.transaction.dao.TransactoionDao;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.transaction.service.api.TransactionService;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.util.TimeZoneUtils;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.model.TransactionRequestDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactoionDao transactoionDao;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;

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
    public Long save(TransactionRequestDto transactionRequestDto) {

        Transaction transaction = transactionMapper.dtoToTransaction(transactionRequestDto);
        Wallet wallet = walletService.getByIdWithUserHolder(transactionRequestDto.getWalletId());
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());

        return transactoionDao.save(transaction).getId();


    }
}
