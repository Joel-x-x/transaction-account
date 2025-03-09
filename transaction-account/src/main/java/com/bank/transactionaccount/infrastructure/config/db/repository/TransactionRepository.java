package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionSchema, UUID> {
    @Query("SELECT t FROM TransactionSchema t WHERE t.accountSchema.id = :accountId")
    Page<TransactionSchema> findAllExcludingDeleted(Pageable pageable, UUID accountId);

    @Query("SELECT t FROM TransactionSchema t WHERE t.accountSchema.id = :accountId AND t.date BETWEEN :startDate AND :endDate")
    List<TransactionSchema> findTransactionsByAccountAndDateRange(UUID accountId, LocalDate startDate, LocalDate endDate);
}
