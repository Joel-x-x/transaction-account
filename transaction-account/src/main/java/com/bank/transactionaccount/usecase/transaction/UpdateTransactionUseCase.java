package com.bank.transactionaccount.usecase.transaction;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionUpdateData;
import com.bank.transactionaccount.usecase.transaction.dto.TransactionCorrectionRegistrationData;

import java.math.BigDecimal;
import java.util.UUID;

import static java.time.LocalDateTime.now;

public class UpdateTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final CreateTransactionUseCase createTransactionUseCase;

    public UpdateTransactionUseCase(TransactionGateway transactionGateway, CreateTransactionUseCase createTransactionUseCase) {
        this.transactionGateway = transactionGateway;
        this.createTransactionUseCase = createTransactionUseCase;
    }

    public Transaction execute(UUID id, ITransactionUpdateData dados) throws TransactionNotFoundException,
            TransactionTypeNotFoundException,
            AccountNotFoundException,
            TransactionBusinessRuleViolationException
             {

        Transaction transactionOld = this.transactionGateway.findById(id)
                .orElseThrow(TransactionNotFoundException::new);

        // Eliminamos el transaction anterior
        transactionOld.setDeleted(true);
        transactionOld.setDeletedAt(now());

        // Lógica para obtener el valor de corrección en base al nuevo monto
         BigDecimal oldAmount = transactionOld.getAmount();
         BigDecimal newAmount = dados.amount();
         UUID transactionTypeId = transactionOld.getTransactionType().getId();

         if(dados.transactionTypeId() != null)
             transactionTypeId = dados.transactionTypeId();

         TransactionCorrectionRegistrationData correctivoData = getTransactionCorrectionRegistrationData(
                 oldAmount,
                 newAmount,
                 transactionOld,
                 transactionTypeId
         );


        this.transactionGateway.update(transactionOld);
         // Crear el movimiento correctivo
        return this.createTransactionUseCase.execute(correctivoData);
    }

    private static TransactionCorrectionRegistrationData getTransactionCorrectionRegistrationData(BigDecimal oldAmount,
                                                                                                  BigDecimal newAmount,
                                                                                                  Transaction transactionOld,
                                                                                                  UUID transactionTypeId) {
        BigDecimal difference = oldAmount.subtract(newAmount);
        BigDecimal correctedAmount = difference.negate();

        // Movimiento correctivo: revertir el monto de la transacción anterior
        return new TransactionCorrectionRegistrationData(
                transactionTypeId,
                correctedAmount,
                true,
                transactionOld.getAccount().getId()
        );
    }
}
