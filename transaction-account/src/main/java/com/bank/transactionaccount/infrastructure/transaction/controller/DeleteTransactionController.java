package com.bank.transactionaccount.infrastructure.transaction.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionPublicData;
import com.bank.transactionaccount.usecase.transaction.DeleteTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteTransactionController {

    private final DeleteTransactionUseCase deleteTransactionUseCase;

    public DeleteTransactionController(DeleteTransactionUseCase deleteTransactionUseCase) {
        this.deleteTransactionUseCase = deleteTransactionUseCase;
    }

    @DeleteMapping("${api.prefix}/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionPublicData deleteTransaction(@PathVariable UUID id) throws TransactionNotFoundException, TransactionBusinessRuleViolationException, AccountNotFoundException, TransactionTypeNotFoundException {
        return new TransactionPublicData(deleteTransactionUseCase.execute(id));
    }
}
