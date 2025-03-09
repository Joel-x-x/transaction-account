package com.bank.transactionaccount.entity.transaction.gateway;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionGateway {
    Transaction create(Transaction transaction);
    Transaction update(Transaction transaction);
    Optional<Transaction> findById(UUID id);
    Page<Transaction> findAllExcludingDeleted(Pageable pageable, UUID accountId);
    List<Transaction> findTransactionsByAccountAndDateRange(UUID accountId, LocalDate startDate, LocalDate endDate);
}
