package com.bank.transactionaccount.infrastructure.transaction.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionPublicData;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionRegistrationData;
import com.bank.transactionaccount.usecase.transaction.CreateTransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public CreateTransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping("${api.prefix}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionPublicData createTransaction(@Valid @RequestBody TransactionRegistrationData dados) throws TransactionTypeNotFoundException, TransactionBusinessRuleViolationException, AccountNotFoundException {
        return new TransactionPublicData(createTransactionUseCase.execute(dados));
    }
}
