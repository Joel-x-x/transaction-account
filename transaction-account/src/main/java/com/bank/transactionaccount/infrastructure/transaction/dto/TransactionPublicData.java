package com.bank.transactionaccount.infrastructure.transaction.dto;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionPublicData;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypePublicData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionPublicData(
        UUID id,
        LocalDate date,
        ITransactionTypePublicData transactionType,
        BigDecimal amount,
        BigDecimal balance,
        Boolean isCorrective,
        UUID accountId
) implements ITransactionPublicData {
    public TransactionPublicData(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getDate(),
                new TransactionTypePublicData(transaction.getTransactionType()),
                transaction.getAmount(),
                transaction.getBalance(),
                transaction.getIsCorrective(),
                transaction.getAccount().getId()
        );
    }
}
