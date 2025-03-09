package com.bank.transactionaccount.infrastructure.transaction.dto;

import com.bank.transactionaccount.usecase.transaction.dto.ITransactionRegistrationData;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRegistrationData(
        @NotNull
        UUID transactionTypeId,
        @NotNull
        BigDecimal amount,
        @NotNull
        UUID accountId,
        boolean isCorrective
) implements ITransactionRegistrationData {
}
