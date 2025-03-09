package com.bank.transactionaccount.usecase.accounttype.dto;

import java.util.UUID;

public interface IAccountTypePublicData {
    UUID id();
    String name();
    String description();
}
