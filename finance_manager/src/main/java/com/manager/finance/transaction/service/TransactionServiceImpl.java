/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:27
 **/

package com.manager.finance.transaction.service;

import com.manager.finance.exception.transaction.TransactionNotFoundException;
import com.manager.finance.mapstruct.mapper.TransactionMapper;
import com.manager.finance.transaction.dao.TransactionDao;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.transaction.service.api.TransactionService;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.model.TransactionRequestDto;
import org.example.model.TransactionResponseDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;

    @Override
    public List<TransactionResponseDto> getAll() {
        ///////////   TO BE REMOVED OR CHANGE IT
        return null;
    }

    @Override
    public List<TransactionResponseDto> findAllByWallet(Long walletId,
                                                        Integer pageNumber,
                                                        Integer pageSize,
                                                        String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy, "id").descending());
        List<Transaction> allByWallet = transactionDao.findAllByWallet(walletService.getByIdWithUserHolder(walletId), paging);
        return transactionMapper.toDtoList(allByWallet);
    }

    @Override
    public TransactionResponseDto getById(Long id) {
        return transactionMapper.toDto(transactionDao.findById(id).orElseThrow(
                () -> new TransactionNotFoundException("No transaction #" + id + " or it has been deleted")));
    }

    @Override
    public TransactionResponseDto update(Long transactionId, TransactionRequestDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction.setId(transactionId);
        return transactionMapper.toDto(transactionDao.save(transaction));
    }

    @Override
    public void delete(Long id) {
        transactionDao.deleteById(id);
    }

    @Override
    public Long save(TransactionRequestDto transactionRequestDto) {
        Transaction transaction = transactionMapper.toEntity(transactionRequestDto);
        Wallet wallet = walletService.getByIdWithUserHolder(transactionRequestDto.getWalletId());
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());
        return transactionDao.save(transaction).getId();
    }
}
