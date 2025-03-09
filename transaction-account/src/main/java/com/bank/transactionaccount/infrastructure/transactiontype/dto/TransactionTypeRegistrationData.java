package com.bank.transactionaccount.infrastructure.transactiontype.dto;

import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypeRegistrationData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TransactionTypeRegistrationData(
        @NotBlank
        @Size(min = 2, max = 50)
        String name,
        @NotBlank
        @Size(min = 2, max = 255)
        String description
) implements ITransactionTypeRegistrationData {
}
