package com.bank.transactionaccount.usecase.transactiontype.dto;

import java.util.UUID;

public interface ITransactionTypePublicData {
    UUID id();
    String name();
    String description();
}
