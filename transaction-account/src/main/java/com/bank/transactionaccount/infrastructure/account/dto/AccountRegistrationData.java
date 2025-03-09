package com.bank.transactionaccount.infrastructure.account.dto;

import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.usecase.account.dto.IAccountRegistrationData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountRegistrationData(
        @NotNull
        UUID accountTypeId,

        @NotBlank
        @Size(min = 2, max = 30)
        String alias,

        @NotNull
        @Positive
        BigDecimal initialBalance,

        @NotNull
        String customerId
) implements IAccountRegistrationData {
}
