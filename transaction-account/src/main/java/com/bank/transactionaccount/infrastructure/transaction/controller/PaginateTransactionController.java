package com.bank.transactionaccount.infrastructure.transaction.controller;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionPublicData;
import com.bank.transactionaccount.usecase.transaction.PaginateTransactionUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaginateTransactionController {

    private final PaginateTransactionUseCase paginateTransactionUseCase;

    public PaginateTransactionController(PaginateTransactionUseCase paginateTransactionUseCase) {
        this.paginateTransactionUseCase = paginateTransactionUseCase;
    }

    @GetMapping("${api.prefix}/transactions/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransactionPublicData> paginateTransactions(
            @PathVariable UUID accountId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<Transaction> customers = this.paginateTransactionUseCase.execute(page, size, accountId);

        return customers.map(TransactionPublicData::new);
    }
}
