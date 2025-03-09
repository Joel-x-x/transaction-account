package com.bank.transactionaccount.usecase.transactiontype;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;

import java.util.UUID;

import static java.time.LocalDateTime.now;

public class DeleteTransactionTypeUseCase {

    private final TransactionTypeGateway transactionTypeGateway;

    public DeleteTransactionTypeUseCase(TransactionTypeGateway transactionTypeGateway) {
        this.transactionTypeGateway = transactionTypeGateway;
    }

    public TransactionType execute(UUID id) throws TransactionTypeNotFoundException {
        TransactionType transactionType = this.transactionTypeGateway.findById(id)
                .orElseThrow(TransactionTypeNotFoundException::new);

        transactionType.setDeleted(true);
        transactionType.setDeletedAt(now());

        this.transactionTypeGateway.update(transactionType);

        return transactionType;
    }
}
