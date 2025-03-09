package com.bank.transactionaccount.usecase.transaction;

import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;

import java.util.UUID;

public class GetTransactionUseCase {

    private final TransactionGateway transactionGateway;

    public GetTransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public Transaction execute(UUID id) throws TransactionNotFoundException {
        return this.transactionGateway.findById(id)
                .orElseThrow(TransactionNotFoundException::new);
    }
}
