package com.bank.transactionaccount.infrastructure.transaction.dto;

import com.bank.transactionaccount.usecase.account.dto.IAccountUpdateData;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionUpdateData;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionUpdateData(
        UUID transactionTypeId,
        BigDecimal amount
) implements ITransactionUpdateData {
}
