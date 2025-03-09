package com.bank.transactionaccount.infrastructure.transactiontype.dto;

import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypePublicData;

import java.util.UUID;

public record TransactionTypePublicData(
        UUID id,
        String name,
        String description
) implements ITransactionTypePublicData {
    public TransactionTypePublicData(TransactionType transactionType) {
        this(
                transactionType.getId(),
                transactionType.getName(),
                transactionType.getDescription()
        );
    }
}
