package com.bank.transactionaccount.entity.account.gateway;

import com.bank.transactionaccount.entity.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountGateway {
    Account create(Account account);
    Account update(Account account);
    Optional<Account> findById(UUID id);
    List<Account> findAllByCustomerId(String customerId);

    Page<Account> findAllExcludingDeleted(Pageable pageable, String customerId);
}
