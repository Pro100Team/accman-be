/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:27
 **/

package com.manager.finance.transaction.service;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.category.service.api.ProfileSubcategoryService;
import com.manager.finance.exception.transaction.TransactionNotFoundException;
import com.manager.finance.mapstruct.mapper.TransactionMapper;
import com.manager.finance.transaction.dao.TransactionDao;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.transaction.service.api.TransactionService;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.service.api.WalletService;
import com.sandbox.model.SortParameter;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import java.util.stream.Collectors;
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
    private final ProfileSubcategoryService profileSubcategoryService;
    private final CategoryService categoryService;
    private final ProfileCategoryService profileCategoryService;

    @Override
    public List<TransactionResponseDto> getAll(Long pageNumber, Long pageSize,
                                               TransactionTypeParameter transactionsType,
                                               Profile profile) {
        List<Transaction> transactions = transactionDao
                .findAllByProfile(profile);
        if (transactionsType != null) {
            transactions = transactions.stream()
                    .filter(t -> t.getTypeOf() == transactionsType)
                    .collect(Collectors.toList());
        }
        PagedListHolder<Transaction> pages = new PagedListHolder<>(transactions);
        pages.setPage(Math.toIntExact(pageNumber));
        pages.setPageSize(Math.toIntExact(pageSize));
        return transactionMapper.toDtoList(pages.getPageList());
    }

    @Override
    public List<TransactionResponseDto> findAllByWallet(Long walletId,
                                                        Long pageNumber,
                                                        Long pageSize,
                                                        SortParameter sortBy, Profile profile) {
        String sortParametr = sortBy.getValue();
        Sort sortPredicate = sortParametr.contains("DESC")
                ? Sort.by(sortParametr.substring(0, sortParametr.length() - 4).toLowerCase())
                .descending()
                : Sort.by(sortParametr.substring(0, sortParametr.length() - 3).toLowerCase())
                .ascending();
        Pageable paging = PageRequest.of(Math.toIntExact(pageNumber),
                Math.toIntExact(pageSize), sortPredicate);
        List<Transaction> allByWallet = transactionDao.findAllByWallet(
                walletService.getById(walletId, profile), paging);
        return transactionMapper.toDtoList(allByWallet);
    }

    @Override
    public TransactionResponseDto getById(Long id) {
        return transactionMapper.transactionToResponseDto(transactionDao.findById(id).orElseThrow(
                () -> new TransactionNotFoundException(
                        "No transaction #" + id + " or it has been deleted")));
    }

    @Override
    @Transactional
    public TransactionResponseDto update(Long transactionId, TransactionRequestDto transactionDto,
                                         Profile profile) {
        ProfileCategory profileCategory = findProfileCategory(transactionDto, profile);
        ProfileSubcategory profileSubcategory = findProfileSubcategory(transactionDto, profile);
        Transaction transaction =
                transactionMapper.requestDtoToTransaction(transactionDto, profileSubcategory,
                        profileCategory);
        transaction.setId(transactionId);
        Wallet wallet = walletService.getById(transactionDto.getWalletId(), profile);
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());
        walletService.update(changeWalletAmount(wallet, transaction), profile);
        return transactionMapper.transactionToResponseDto(transactionDao.save(transaction));
    }

    @Override
    public void delete(Long id, Profile profile) {
        Transaction transaction = transactionDao.findById(id).orElseThrow(
                () -> new TransactionNotFoundException(
                        "No transaction #" + id + " or it has been deleted"));

        Wallet wallet = transaction.getWallet();
        walletService.update(backChangeWalletAmount(wallet, transaction), profile);

        transactionDao.deleteById(id);
    }

    @Override
    @Transactional
    public Long save(TransactionRequestDto transactionRequestDto, Profile profile) {
        ProfileCategory profileCategory = findProfileCategory(transactionRequestDto, profile);
        ProfileSubcategory profileSubcategory =
                findProfileSubcategory(transactionRequestDto, profile);
        Transaction transaction =
                transactionMapper.requestDtoToTransaction(transactionRequestDto, profileSubcategory,
                        profileCategory);
        Wallet wallet =
                walletService.getById(transactionRequestDto.getWalletId(), profile);
        transaction.setWallet(wallet);
        transaction.setCurrency(wallet.getCurrency());
        walletService.update(changeWalletAmount(wallet, transaction), profile);
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

    private ProfileSubcategory findProfileSubcategory(TransactionRequestDto transactionDto,
                                                      Profile profile) {
        Category category =
                categoryService.getByNameOrCreate(transactionDto.getSubcategory());
        return profileSubcategoryService.findByCategoryId(category, profile);
    }

    private ProfileCategory findProfileCategory(TransactionRequestDto transactionDto,
                                                Profile profile) {
        Category category =
                categoryService.getByNameOrCreate(transactionDto.getCategory().getName());
        return profileCategoryService.getByCategoryIdAndProfile(category, profile);
    }
}
