package com.bank.transactionaccount.infrastructure.config.db.repository;

import com.bank.transactionaccount.infrastructure.config.db.schema.AccountSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountSchema, UUID> {

    @Query("SELECT a FROM AccountSchema a WHERE a.deleted = false AND a.customerId = :customerId")
    Page<AccountSchema> findAllExcludingDeleted(Pageable pageable, String customerId);

    @Query("SELECT a FROM AccountSchema a WHERE a.deleted = false AND a.customerId = :customerId")
    List<AccountSchema> findAllByCustomerId(String customerId);

//    @Query("""
//            SELECT a.accountNumber AS numero, at.name AS tipo, a.initialBalance AS saldoInicial, a.deleted AS estado, SUM(t.amount) AS movimiento, (a.initialBalance + SUM(t.amount)) AS saldoDisponible
//                        FROM AccountSchema a JOIN a.transactions t JOIN a.accountTypeSchema at
//                                    WHERE a.deleted = false AND a.customerId = :customerId AND t.date BETWEEN :start AND :end
//                                                GROUP BY a.accountNumber, a.customerId, at.name, a.initialBalance, a.deleted
//            """)
//    List<AccountSchema> findAllByCustomerIdAndTransactionDateRange(
//            String customerId,
//            LocalDate start,
//            LocalDate end
//    );

}
