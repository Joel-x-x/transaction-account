package com.bank.transactionaccount.infrastructure.config.db.schema;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "transactions")
public class TransactionSchema extends AbstractEntitySchema<UUID> {

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionTypeSchema transactionTypeSchema;

    @Column(name = "amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "balance", nullable = false, precision = 18, scale = 2)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountSchema accountSchema;

    @PostLoad
    protected void initDefaults() {
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
