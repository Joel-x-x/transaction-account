package com.bank.transactionaccount.usecase.transactiontype;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;

import java.util.UUID;

public class GetTransactionTypeUseCase {

    private final TransactionTypeGateway transactionTypeGateway;

    public GetTransactionTypeUseCase(TransactionTypeGateway transactionTypeGateway) {
        this.transactionTypeGateway = transactionTypeGateway;
    }

    public TransactionType execute(UUID id) throws TransactionTypeNotFoundException {
        return this.transactionTypeGateway
                .findById(id)
                .orElseThrow(TransactionTypeNotFoundException::new);
    }
}
