package com.bank.transactionaccount.infrastructure.transactiontype.gateway;

import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.infrastructure.config.db.repository.TransactionTypeRepository;
import com.bank.transactionaccount.infrastructure.config.db.schema.TransactionTypeSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionTypeDatabaseGateway implements TransactionTypeGateway {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeDatabaseGateway(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public TransactionType create(TransactionType accountType) {
        return this.transactionTypeRepository.save(new TransactionTypeSchema(accountType)).toTransactionType();
    }

    @Override
    public TransactionType update(TransactionType accountType) {
        return this.transactionTypeRepository.save(new TransactionTypeSchema(accountType)).toTransactionType();
    }

    @Override
    public Optional<TransactionType> findById(UUID id) {
        return this.transactionTypeRepository
                .findById(id)
                .map(TransactionTypeSchema::toTransactionType);
    }

    @Override
    public List<TransactionType> findAll() {
        return this.transactionTypeRepository
                .findAll()
                .stream().map(TransactionTypeSchema::toTransactionType).toList();
    }
}
