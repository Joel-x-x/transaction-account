package com.bank.transactionaccount.entity.transactiontype.gateway;

import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionTypeGateway {
    TransactionType create(TransactionType accountType);
    TransactionType update(TransactionType accountType);
    Optional<TransactionType> findById(UUID id);
    List<TransactionType> findAll();
}
