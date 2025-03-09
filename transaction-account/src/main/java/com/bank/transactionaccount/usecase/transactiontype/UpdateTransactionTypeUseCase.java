package com.bank.transactionaccount.usecase.transactiontype;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypeUpdateData;

import java.util.UUID;

public class UpdateTransactionTypeUseCase {

    private final TransactionTypeGateway transactionTypeGateway;

    public UpdateTransactionTypeUseCase(TransactionTypeGateway transactionTypeGateway) {
        this.transactionTypeGateway = transactionTypeGateway;
    }

    public TransactionType execute(UUID id, ITransactionTypeUpdateData dados) throws TransactionTypeNotFoundException {
        TransactionType transactionType = this.transactionTypeGateway.findById(id)
                .orElseThrow(TransactionTypeNotFoundException::new);

        if(!dados.name().isBlank())
            transactionType.setName(dados.name());
        if(!dados.description().isBlank())
            transactionType.setDescription(dados.description());

        return this.transactionTypeGateway.update(transactionType);
    }
}
