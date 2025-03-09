package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.AccountTypeSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountTypeRepository extends JpaRepository<AccountTypeSchema, UUID> {
}
