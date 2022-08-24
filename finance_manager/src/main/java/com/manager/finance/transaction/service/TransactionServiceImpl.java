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
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import com.sandbox.model.SortParameter;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final TransactionMapper transactionMapper;
    private final WalletService walletService;
    private final ProfileService profileService;

    @Override
    public List<TransactionResponseDto> getAll(Long pageNumber, Long pageSize) {
        List<Transaction> allByWallet = transactionDao
                .findAllByProfile(profileService.findByUserIdWithValidation());
        PagedListHolder<Transaction> pages = new PagedListHolder<>(allByWallet);
        pages.setPage(Math.toIntExact(pageNumber));
        pages.setPageSize(Math.toIntExact(pageSize));
        return transactionMapper.toDtoList(pages.getPageList());
    }

    @Override
    public List<TransactionResponseDto> findAllByWallet(Long walletId,
                                                        Long pageNumber,
                                                        Long pageSize,
                                                        SortParameter sortBy) {
        String sortParametr = sortBy.getValue();
        Sort sortPredicate = sortParametr.contains("DESC")
                ? Sort.by(sortParametr.substring(0, sortParametr.length() - 4).toLowerCase())
                        .descending()
                : Sort.by(sortParametr.substring(0, sortParametr.length() - 3).toLowerCase())
                        .ascending();
        Pageable paging = PageRequest.of(Math.toIntExact(pageNumber),
                Math.toIntExact(pageSize), sortPredicate);
        List<Transaction> allByWallet = transactionDao.findAllByWallet(
                walletService.getByIdWithUserHolder(walletId), paging);
        return transactionMapper.transactionsListToResponseDtoList(allByWallet);
    }

    @Override
    public TransactionResponseDto getById(Long id) {
        return transactionMapper.transactionToResponseDto(transactionDao.findById(id).orElseThrow(
                () -> new TransactionNotFoundException(
                        "No transaction #" + id + " or it has been deleted")));
    }

    @Override
    @Transactional
    public TransactionResponseDto update(Long transactionId, TransactionRequestDto transactionDto) {
        Transaction transaction = transactionMapper.requestDtoToTransaction(transactionDto);
        transaction.setId(transactionId);
        Wallet wallet = walletService.getByIdWithUserHolder(transactionDto.getWalletId());
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());
        walletService.update(changeWalletAmount(wallet, transaction));
        return transactionMapper.transactionToResponseDto(transactionDao.save(transaction));
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = transactionDao.findById(id).orElseThrow(
                () -> new TransactionNotFoundException(
                        "No transaction #" + id + " or it has been deleted"));

        Wallet wallet = transaction.getWallet();
        walletService.update(backChangeWalletAmount(wallet, transaction));

        transactionDao.deleteById(id);
    }

    @Override
    @Transactional
    public Long save(TransactionRequestDto transactionRequestDto) {
        Transaction transaction = transactionMapper.requestDtoToTransaction(transactionRequestDto);
        Wallet wallet = walletService.getByIdWithUserHolder(transactionRequestDto.getWalletId());
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());
        walletService.update(changeWalletAmount(wallet, transaction));
        return transactionDao.save(transaction).getId();
    }

    private Wallet changeWalletAmount(Wallet wallet, Transaction transaction) {
        if (transaction.getTypeOf() == TransactionTypeParameter.EXPENSE) {
            wallet.setAmount(wallet.getAmount() - transaction.getAmount());
        } else {
            wallet.setAmount(wallet.getAmount() + transaction.getAmount());
        }
        return wallet;
    }

    private Wallet backChangeWalletAmount(Wallet wallet, Transaction transaction) {
        if (transaction.getTypeOf() == TransactionTypeParameter.EXPENSE) {
            wallet.setAmount(wallet.getAmount() + transaction.getAmount());
        } else {
            wallet.setAmount(wallet.getAmount() - transaction.getAmount());
        }
        return wallet;
    }
}
