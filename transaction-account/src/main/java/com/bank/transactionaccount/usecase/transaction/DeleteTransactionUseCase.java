package com.bank.transactionaccount.usecase.transaction;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionRegistrationData;
import com.bank.transactionaccount.usecase.transaction.dto.TransactionCorrectionRegistrationData;

import java.util.UUID;

import static java.time.LocalDateTime.now;

public class DeleteTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final CreateTransactionUseCase createTransactionUseCase;

    public DeleteTransactionUseCase(TransactionGateway transactionGateway, CreateTransactionUseCase createTransactionUseCase) {
        this.transactionGateway = transactionGateway;
        this.createTransactionUseCase = createTransactionUseCase;
    }

    public Transaction execute(UUID id) throws TransactionNotFoundException,
            TransactionTypeNotFoundException,
            AccountNotFoundException,
            TransactionBusinessRuleViolationException {

        Transaction transactionOld = this.transactionGateway.findById(id)
                .orElseThrow(TransactionNotFoundException::new);

        transactionOld.setDeleted(true);
        transactionOld.setDeletedAt(now());

        // Movimiento correctivo
        ITransactionRegistrationData correctivoData = new TransactionCorrectionRegistrationData(
                transactionOld.getTransactionType().getId(),
                transactionOld.getAmount().negate(),
                true,
                transactionOld.getAccount().getId()
        );

        this.transactionGateway.update(transactionOld);

        return this.createTransactionUseCase.execute(correctivoData);
    }
}
