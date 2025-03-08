package com.bank.transactionaccount.entity.transaction.gateway;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface TransactionGateway {
    Transaction create(Transaction transaction);
    Transaction update(Transaction transaction);
    Optional<Transaction> findById(UUID id);
    Page<Transaction> findAllExcludingDeleted(int page, int size);
}
