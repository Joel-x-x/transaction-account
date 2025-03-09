package com.bank.transactionaccount.entity.account.model;

import com.bank.transactionaccount.entity.AbstractEntity;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Account extends AbstractEntity<UUID> {
    private String accountNumber;
    private String alias;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private String customerId;
    private List<Transaction> transactions;
}
