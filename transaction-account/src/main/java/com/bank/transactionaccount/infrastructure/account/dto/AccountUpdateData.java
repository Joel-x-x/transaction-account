package com.bank.transactionaccount.infrastructure.account.dto;

import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.usecase.account.dto.IAccountUpdateData;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountUpdateData(
        UUID id,
        String alias,
        UUID transactionTypeId
) implements IAccountUpdateData {
}
