package com.bank.transactionaccount.infrastructure.accounttype.dto;

import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypePublicData;

import java.util.UUID;

public record AccountTypePublicData(
        UUID id,
        String name,
        String description
) implements IAccountTypePublicData {
    public AccountTypePublicData(AccountType accountType) {
        this(
                accountType.getId(),
                accountType.getName(),
                accountType.getDescription()
        );
    }
}
