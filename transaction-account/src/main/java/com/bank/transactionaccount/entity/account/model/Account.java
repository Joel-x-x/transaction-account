package com.bank.transactionaccount.entity.account.model;

import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class Account {
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal initialBalance;
    private UUID customerId;
}
