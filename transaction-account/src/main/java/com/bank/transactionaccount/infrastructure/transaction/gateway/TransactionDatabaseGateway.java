package com.bank.transactionaccount.infrastructure.transaction.gateway;

import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.infrastructure.config.db.repository.TransactionRepository;
import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionDatabaseGateway implements TransactionGateway {

    private final TransactionRepository transactionRepository;

    public TransactionDatabaseGateway(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(Transaction transaction) {
        return this.transactionRepository.save(new TransactionSchema(transaction)).toTransaction();
    }

    @Override
    public Transaction update(Transaction transaction) {
        return this.transactionRepository.save(new TransactionSchema(transaction)).toTransaction();
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return transactionRepository
                .findById(id)
                .map(TransactionSchema::toTransaction);
    }

    @Override
    public Page<Transaction> findAllExcludingDeleted(Pageable pageable, UUID accountId) {
        return transactionRepository
                .findAllExcludingDeleted(pageable, accountId)
                .map(TransactionSchema::toTransaction);
    }

    @Override
    public List<Transaction> findTransactionsByAccountAndDateRange(UUID accountId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository
                .findTransactionsByAccountAndDateRange(accountId, startDate, endDate)
                .stream()
                .map(TransactionSchema::toTransaction)
                .toList();
    }
}
