package com.bank.transactionaccount.infrastructure.config.db.schema;

import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "account_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uk_account_type_name")
})
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountTypeSchema extends AbstractEntitySchema<UUID> {
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    public AccountTypeSchema(AccountType accountType) {
        this.setId(accountType.getId());
        this.setName(accountType.getName());
        this.setDescription(accountType.getDescription());
        this.setCreatedAt(accountType.getCreatedAt());
        this.setUpdatedAt(accountType.getUpdatedAt());
        this.setDeletedAt(accountType.getDeletedAt());
        this.setDeleted(accountType.isDeleted());
    }

    public AccountType toAccountType() {
        return AccountType.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .deleted(this.isDeleted())
                .build();
    }
}
