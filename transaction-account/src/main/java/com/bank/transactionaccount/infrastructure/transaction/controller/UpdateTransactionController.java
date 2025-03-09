package com.bank.transactionaccount.infrastructure.transaction.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionPublicData;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionUpdateData;
import com.bank.transactionaccount.usecase.transaction.UpdateTransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateTransactionController {

    private final UpdateTransactionUseCase updateTransactionUseCase;

    public UpdateTransactionController(UpdateTransactionUseCase updateTransactionUseCase) {
        this.updateTransactionUseCase = updateTransactionUseCase;
    }

    @PutMapping("${api.prefix}/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionPublicData updateTransaction(@PathVariable UUID id, @Valid @RequestBody TransactionUpdateData dados) throws TransactionNotFoundException, TransactionBusinessRuleViolationException, AccountNotFoundException, TransactionTypeNotFoundException {
        return new TransactionPublicData(updateTransactionUseCase.execute(id, dados));
    }
}
