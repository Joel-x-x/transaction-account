package com.bank.transactionaccount.entity.accounttype.gateway;

import com.bank.transactionaccount.entity.accounttype.model.AccountType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountTypeGateway {
    AccountType create(AccountType accountType);
    AccountType update(AccountType accountType);
    Optional<AccountType> findById(UUID id);
    List<AccountType> findAll();
}
