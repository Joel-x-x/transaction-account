package com.bank.transactionaccount.usecase.accounttype.dto;

import java.util.UUID;

public interface IAccountTypeUpdateData {
    UUID id();
    String name();
    String description();
}
