package com.bank.transactionaccount.infrastructure.account.gateway;

import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.infrastructure.config.db.repository.AccountRepository;
import com.bank.transactionaccount.infrastructure.config.db.schema.AccountSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountDatabaseGateway implements AccountGateway {

    private final AccountRepository accountRepository;

    public AccountDatabaseGateway(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return this.accountRepository.save(new AccountSchema(account)).toAccount();
    }

    @Override
    public Account update(Account account) {
        return this.accountRepository.save(new AccountSchema(account)).toAccount();
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return accountRepository
                .findById(id)
                .map(AccountSchema::toAccount);
    }

    @Override
    public List<Account> findAllByCustomerId(String customerId) {
        return accountRepository
                .findAllByCustomerId(customerId)
                .stream()
                .map(AccountSchema::toAccount).toList();
    }

    @Override
    public Page<Account> findAllExcludingDeleted(Pageable pageable, String customerId) {
        return accountRepository
                .findAllExcludingDeleted(pageable, customerId)
                .map(AccountSchema::toAccount);
    }
}
