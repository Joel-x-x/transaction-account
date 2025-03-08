package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionTypeSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeSchema, UUID> {
}
