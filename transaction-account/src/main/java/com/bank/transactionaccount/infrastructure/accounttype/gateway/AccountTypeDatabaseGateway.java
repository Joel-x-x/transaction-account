package com.bank.transactionaccount.infrastructure.accounttype.gateway;

import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.infrastructure.config.db.repository.AccountTypeRepository;
import com.bank.transactionaccount.infrastructure.config.db.schema.AccountTypeSchema;
import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionTypeSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountTypeDatabaseGateway implements AccountTypeGateway {

    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeDatabaseGateway(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public AccountType create(AccountType accountType) {
        return this.accountTypeRepository.save(new AccountTypeSchema(accountType)).toAccountType();
    }

    @Override
    public AccountType update(AccountType accountType) {
        return this.accountTypeRepository.save(new AccountTypeSchema(accountType)).toAccountType();
    }

    @Override
    public Optional<AccountType> findById(UUID id) {
        return accountTypeRepository
                .findById(id)
                .map(AccountTypeSchema::toAccountType);
    }

    @Override
    public List<AccountType> findAll() {
        return this.accountTypeRepository
                .findAll()
                .stream().map(AccountTypeSchema::toAccountType).toList();
    }
}
