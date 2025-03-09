package com.bank.transactionaccount.infrastructure.config.db.schema;

import com.bank.transactionaccount.entity.account.model.Account;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
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

    @Column(name = "alias", nullable = false, unique = true, length = 30)
    private String alias;

    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeSchema accountTypeSchema;

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @OneToMany(mappedBy = "accountSchema", fetch = FetchType.LAZY)
    private List<TransactionSchema> transactions;

    @PostLoad
    protected void initDefaults() {
        if (initialBalance == null) {
            initialBalance = BigDecimal.ZERO;
        }
    }

    public AccountSchema(Account account) {
        this.setId(account.getId());
        this.setAccountNumber(account.getAccountNumber());
        this.setAlias(account.getAlias());
        this.setAccountTypeSchema(new AccountTypeSchema(account.getAccountType()));
        this.setInitialBalance(account.getInitialBalance());
        this.setCustomerId(account.getCustomerId());
        this.setCreatedAt(account.getCreatedAt());
        this.setUpdatedAt(account.getUpdatedAt());
        this.setDeletedAt(account.getDeletedAt());
        this.setDeleted(account.isDeleted());
    }

    public Account toAccount() {
        return Account.builder()
                .id(this.getId())
                .accountNumber(this.getAccountNumber())
                .alias(this.getAlias())
                .accountType(this.getAccountTypeSchema().toAccountType())
                .initialBalance(this.getInitialBalance())
                .customerId(this.getCustomerId())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .deleted(this.isDeleted())
                .build();
    }
}
