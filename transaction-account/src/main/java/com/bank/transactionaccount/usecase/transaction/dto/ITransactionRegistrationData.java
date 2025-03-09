package com.bank.transactionaccount.usecase.transaction.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITransactionRegistrationData {
    UUID transactionTypeId();
    BigDecimal amount();
    UUID accountId();
    boolean isCorrective();
}
