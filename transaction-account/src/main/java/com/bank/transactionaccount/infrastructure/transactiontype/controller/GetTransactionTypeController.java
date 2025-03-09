package com.bank.transactionaccount.infrastructure.transactiontype.controller;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.usecase.transactiontype.DeleteTransactionTypeUseCase;
import com.bank.transactionaccount.usecase.transactiontype.GetTransactionTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetTransactionTypeController {

    private final GetTransactionTypeUseCase getTransactionTypeUseCase;

    public GetTransactionTypeController(GetTransactionTypeUseCase getTransactionTypeUseCase) {
        this.getTransactionTypeUseCase = getTransactionTypeUseCase;
    }

    @GetMapping("${api.prefix}/transaction-types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionTypePublicData getTransactionType(@PathVariable UUID id) throws TransactionTypeNotFoundException {
        return new TransactionTypePublicData(getTransactionTypeUseCase.execute(id));
    }
}
