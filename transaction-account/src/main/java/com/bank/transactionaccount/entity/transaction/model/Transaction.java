package com.bank.transactionaccount.entity.transaction.model;

import com.bank.transactionaccount.entity.AbstractEntity;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Transaction extends AbstractEntity<UUID> {
    private LocalDate date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private Account account;
    private Boolean isCorrective;
    private UUID accountId;

    @PrePersist
    public void initDefaults() {
        if (date == null) {
            date = LocalDate.now();
        }
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
        if (isCorrective == null) {
            isCorrective = false;
        }
    }
}
