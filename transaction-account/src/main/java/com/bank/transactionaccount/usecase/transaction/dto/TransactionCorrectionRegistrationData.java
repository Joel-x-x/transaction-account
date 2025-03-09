package com.bank.transactionaccount.usecase.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCorrectionRegistrationData(
        @NotNull
        UUID transactionTypeId,

        @NotNull
        BigDecimal amount,

        @NotNull
        boolean isCorrective,

        @NotNull
        UUID accountId
) implements ITransactionRegistrationData {
}
