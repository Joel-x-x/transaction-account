package com.bank.transactionaccount.infrastructure.config.db.schema;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountSchema extends AbstractEntitySchema<UUID> {

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeSchema accountTypeSchema;

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance;

    @PostLoad
    protected void initDefaults() {
        if (initialBalance == null) {
            initialBalance = BigDecimal.ZERO;
        }
    }
}
