package com.bank.transactionaccount.infrastructure.transactiontype.controller;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.usecase.transactiontype.CreateTransactionTypeUseCase;
import com.bank.transactionaccount.usecase.transactiontype.DeleteTransactionTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteTransactionTypeController {

    private final DeleteTransactionTypeUseCase deleteTransactionTypeUseCase;

    public DeleteTransactionTypeController(DeleteTransactionTypeUseCase deleteTransactionTypeUseCase) {
        this.deleteTransactionTypeUseCase = deleteTransactionTypeUseCase;
    }

    @DeleteMapping("${api.prefix}/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionTypePublicData deleteCustomer(@PathVariable UUID id) throws TransactionTypeNotFoundException {
        return new TransactionTypePublicData(deleteTransactionTypeUseCase.execute(id));
    }
}
