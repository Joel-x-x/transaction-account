package com.bank.transactionaccount.usecase.transaction.dto;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.usecase.account.dto.IAccountPublicData;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypePublicData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface ITransactionPublicData {
    UUID id();
    LocalDate date();
    ITransactionTypePublicData transactionType();
    BigDecimal amount();
    BigDecimal balance();
    UUID accountId();
}
