package com.bank.transactionaccount.entity.transaction.model;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class Transaction {
    private LocalDate date;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;
    private Account account;

    // Si deseas manejar valores por defecto
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
    }
}
