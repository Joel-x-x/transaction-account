package com.bank.transactionaccount.usecase.transactiontype;

import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypeRegistrationData;

public class CreateTransactionTypeUseCase {
    private final TransactionTypeGateway transactionTypeGateway;

    public CreateTransactionTypeUseCase(TransactionTypeGateway transactionTypeGateway) {
        this.transactionTypeGateway = transactionTypeGateway;
    }

    public TransactionType execute(ITransactionTypeRegistrationData dados) {
        TransactionType transactionType = TransactionType.builder()
                .name(dados.name())
                .description(dados.description())
                .build();

        return this.transactionTypeGateway.create(transactionType);
    }
}
