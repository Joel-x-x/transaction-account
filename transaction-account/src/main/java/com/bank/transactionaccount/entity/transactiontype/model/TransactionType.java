package com.bank.transactionaccount.entity.transactiontype.model;

import com.bank.transactionaccount.entity.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class TransactionType extends AbstractEntity<UUID> {
    private String name;
    private String description;
}
