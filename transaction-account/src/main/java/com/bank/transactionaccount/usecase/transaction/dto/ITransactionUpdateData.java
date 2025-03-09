package com.bank.transactionaccount.usecase.transaction.dto;

import com.bank.transactionaccount.usecase.account.dto.IAccountPublicData;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypePublicData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface ITransactionUpdateData {
    UUID transactionTypeId();
    BigDecimal amount();
}
