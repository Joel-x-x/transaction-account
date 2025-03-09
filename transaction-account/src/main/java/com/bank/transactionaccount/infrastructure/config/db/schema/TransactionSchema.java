package com.bank.transactionaccount.infrastructure.config.db.schema;

import com.bank.transactionaccount.entity.transaction.model.Transaction;
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

    @Column(name = "is_corrective")
    private Boolean isCorrective;

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
        if (isCorrective == null) {
            isCorrective = false;
        }
    }

    public TransactionSchema(Transaction transaction) {
        this.setId(transaction.getId());
        this.setDate(transaction.getDate());
        this.setTransactionTypeSchema(new TransactionTypeSchema(transaction.getTransactionType()));
        this.setAmount(transaction.getAmount());
        this.setBalance(transaction.getBalance());
        this.setIsCorrective(transaction.getIsCorrective());
        this.setAccountSchema(new AccountSchema(transaction.getAccount()));
        this.setCreatedAt(transaction.getCreatedAt());
        this.setUpdatedAt(transaction.getUpdatedAt());
        this.setDeletedAt(transaction.getDeletedAt());
        this.setDeleted(transaction.isDeleted());
    }

    public Transaction toTransaction() {
        return Transaction.builder()
                .id(this.getId())
                .date(this.getDate())
                .transactionType(this.getTransactionTypeSchema().toTransactionType())
                .amount(this.getAmount())
                .balance(this.getBalance())
                .isCorrective(this.getIsCorrective())
                .account(this.getAccountSchema().toAccount())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .deleted(this.isDeleted())
                .build();
    }
}
