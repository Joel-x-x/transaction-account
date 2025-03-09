package com.bank.transactionaccount.usecase.transactiontype;

import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;

import java.util.List;

public class ListTransactionTypeUseCase {

    private final TransactionTypeGateway transactionTypeGateway;

    public ListTransactionTypeUseCase(TransactionTypeGateway transactionTypeGateway) {
        this.transactionTypeGateway = transactionTypeGateway;
    }

    public List<TransactionType> execute() {
        return this.transactionTypeGateway.findAll();
    }
}
