package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.AccountTypeSchema;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountTypeSchema, UUID> {
}
