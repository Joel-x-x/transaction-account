package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionSchema, UUID> {
    @Query("SELECT t FROM TransactionSchema t WHERE t.deleted = false")
    Page<Transaction> findAllExcludingDeleted(int page, int size);
}
