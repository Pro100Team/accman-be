/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:29
 **/

package com.manager.finance.transaction.controller;

import com.manager.finance.transaction.service.api.TransactionService;
import com.sandbox.api.TransactionsApi;
import com.sandbox.model.FilterParameter;
import com.sandbox.model.SortParameter;
import com.sandbox.model.TransactionRequestDto;
import com.sandbox.model.TransactionResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TransactionController implements TransactionsApi {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Long> createTransaction(
            @Valid TransactionRequestDto transactionRequestDto) {

        return new ResponseEntity<>(transactionService.save(transactionRequestDto),
                                    HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTtransactionById(Long transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TransactionResponseDto> getTransactionById(Long transactionId) {
        return ResponseEntity.ok(transactionService.getById(transactionId));
    }

    @Override
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(
            @NotNull @Valid SortParameter sortBy,
            @NotNull @Valid Long page, @NotNull @Valid Long size,
            @Valid TransactionTypeParameter transactionsType,
            @Valid List<FilterParameter> filterBy,
            @Valid Long walletId) {
        return walletId == null
                ? ResponseEntity.ok(transactionService.getAll(page, size))
                : ResponseEntity.ok(transactionService.findAllByWallet(
                        walletId, page, size, sortBy));
    }

    @Override
    public ResponseEntity<TransactionResponseDto> updateTransactionById(
            Long transactionId,
            @Valid TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionService.update(transactionId, transactionRequestDto));
    }
}
