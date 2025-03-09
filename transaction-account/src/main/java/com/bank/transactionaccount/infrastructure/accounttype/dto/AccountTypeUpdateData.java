package com.bank.transactionaccount.infrastructure.accounttype.dto;

import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypeUpdateData;

import java.util.UUID;

public record AccountTypeUpdateData(
        UUID id,
        String name,
        String description
) implements IAccountTypeUpdateData {
}
