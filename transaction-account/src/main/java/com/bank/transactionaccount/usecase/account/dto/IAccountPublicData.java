package com.bank.transactionaccount.usecase.account.dto;

import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;

import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountPublicData {
    UUID id();
    String accountNumber();
    String alias();
    AccountTypePublicData accountType();
    BigDecimal initialBalance();
    String customerId();
}
