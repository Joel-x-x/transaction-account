package com.bank.transactionaccount.infrastructure.accounttype.dto;

import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypeRegistrationData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AccountTypeRegistrationData(
        @NotBlank
        @Size(min = 2, max = 50)
        String name,
        @NotBlank
        @Size(min = 2, max = 255)
        String description
) implements IAccountTypeRegistrationData {
}
