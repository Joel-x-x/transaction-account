package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.AccountSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountSchema, UUID> {

    @Query("SELECT a FROM AccountSchema a WHERE a.deleted = false")
    Page<AccountSchema> findAllExcludingDeleted(int page, int size);
}
