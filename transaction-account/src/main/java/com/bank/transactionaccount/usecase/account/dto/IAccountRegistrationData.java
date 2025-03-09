package com.bank.transactionaccount.usecase.account.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountRegistrationData {
    UUID accountTypeId();
    String alias();
    BigDecimal initialBalance();
    String customerId();
}
