package com.bank.transactionaccount.entity.accounttype.model;

import com.bank.transactionaccount.entity.AbstractEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
public class AccountType extends AbstractEntity<UUID> {
    private String name;
    private String description;
}
