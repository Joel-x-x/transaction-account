package com.bank.transactionaccount.infrastructure.transaction.controller;

import com.bank.transactionaccount.entity.transaction.exception.TransactionNotFoundException;
import com.bank.transactionaccount.infrastructure.transaction.dto.TransactionPublicData;
import com.bank.transactionaccount.usecase.transaction.GetTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetTransactionController {

    private final GetTransactionUseCase getTransactionUseCase;

    public GetTransactionController(GetTransactionUseCase getTransactionUseCase) {
        this.getTransactionUseCase = getTransactionUseCase;
    }

    @GetMapping("${api.prefix}/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionPublicData getTrasaction(@PathVariable UUID id) throws TransactionNotFoundException {
        return new TransactionPublicData(getTransactionUseCase.execute(id));
    }
}
