package com.bank.transactionaccount.usecase.transaction;

import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class PaginateTransactionUseCase {

    private final TransactionGateway transactionGateway;

    public PaginateTransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public Page<Transaction> execute(int page, int size, UUID accountId) {
        Pageable pageable = PageRequest.of(page, size);

        return this.transactionGateway.findAllExcludingDeleted(pageable, accountId);
    }
}
