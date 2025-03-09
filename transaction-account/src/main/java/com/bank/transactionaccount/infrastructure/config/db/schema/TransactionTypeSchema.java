package com.bank.transactionaccount.infrastructure.config.db.schema;

import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "transaction_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uk_transaction_type_name")
})
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionTypeSchema extends AbstractEntitySchema<UUID> {
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    public TransactionTypeSchema(TransactionType transactionType) {
        this.setId(transactionType.getId());
        this.setName(transactionType.getName());
        this.setDescription(transactionType.getDescription());
        this.setCreatedAt(transactionType.getCreatedAt());
        this.setUpdatedAt(transactionType.getUpdatedAt());
        this.setDeletedAt(transactionType.getDeletedAt());
        this.setDeleted(transactionType.isDeleted());
    }

    public TransactionType toTransactionType() {
        return TransactionType.builder()
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
