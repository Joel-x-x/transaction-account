package com.bank.transactionaccount.entity.account.gateway;

import com.bank.transactionaccount.entity.account.model.Account;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface AccountGateway {
    Account create(Account account);
    Account update(Account account);
    Optional<Account> findById(UUID id);
    Page<Account> findAllExcludingDeleted(int page, int size);
}
