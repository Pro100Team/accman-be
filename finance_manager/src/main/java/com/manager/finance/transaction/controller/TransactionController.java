/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:29
 **/

package com.manager.finance.transaction.controller;

import com.manager.finance.transaction.service.api.TransactionService;
import com.sandbox.model.CreateTransactionRequest;
import com.sandbox.model.FilterParameter;
import com.sandbox.model.SortParameter;
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
public class TransactionController implements TrApiTest {

    private final TransactionService transactionService;

    @Override
    public ResponseEntity<Long> createTransaction(
            @NotNull @Valid TransactionTypeParameter transactionsType,
            @Valid CreateTransactionRequest createTransactionRequest) {
        Long save = transactionService.save(createTransactionRequest);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTtransactionById(Long transactionId) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionResponseDto> getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(
            @NotNull @Valid SortParameter sortBy, @NotNull @Valid Long page,
            @NotNull @Valid Long size, @Valid TransactionTypeParameter transactionsType,
            @Valid List<FilterParameter> filterBy, @Valid Long walletId) {
        return null;
    }

    @Override
    public ResponseEntity<TransactionResponseDto> updateTransactionById(
            @NotNull @Valid TransactionTypeParameter transactionsType, Long transactionId,
            @Valid CreateTransactionRequest createTransactionRequest) {
        return null;
    }

}
