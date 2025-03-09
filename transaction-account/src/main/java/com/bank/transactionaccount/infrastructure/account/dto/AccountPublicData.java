package com.bank.transactionaccount.infrastructure.account.dto;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.usecase.account.dto.IAccountPublicData;
import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypePublicData;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountPublicData(
        UUID id,
        String accountNumber,
        String alias,
        AccountTypePublicData accountType,
        BigDecimal initialBalance,
        String customerId
) implements IAccountPublicData {
    public AccountPublicData(Account account) {
        this(
            account.getId(),
            account.getAccountNumber(),
            account.getAlias(),
            new AccountTypePublicData(account.getAccountType()),
            account.getInitialBalance(),
            account.getCustomerId()
        );
    }
}
