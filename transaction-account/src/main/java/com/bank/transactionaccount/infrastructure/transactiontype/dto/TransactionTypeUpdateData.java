package com.bank.transactionaccount.infrastructure.transactiontype.dto;

import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypeUpdateData;

import java.util.UUID;

public record TransactionTypeUpdateData(
        UUID id,
        String name,
        String description
) implements ITransactionTypeUpdateData {
}
