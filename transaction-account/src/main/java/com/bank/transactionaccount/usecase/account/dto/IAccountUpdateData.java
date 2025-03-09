package com.bank.transactionaccount.usecase.account.dto;

import java.util.UUID;

public interface IAccountUpdateData {
    UUID id();
    String alias();
    UUID transactionTypeId();
}
