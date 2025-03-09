package com.bank.transactionaccount.infrastructure.transactiontype.controller;

import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypeUpdateData;
import com.bank.transactionaccount.usecase.transactiontype.ListTransactionTypeUseCase;
import com.bank.transactionaccount.usecase.transactiontype.UpdateTransactionTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateTransactionTypeController {

    private final UpdateTransactionTypeUseCase updateTransactionTypeUseCase;

    public UpdateTransactionTypeController(UpdateTransactionTypeUseCase updateTransactionTypeUseCase) {
        this.updateTransactionTypeUseCase = updateTransactionTypeUseCase;
    }

    @PutMapping("${api.prefix}/transaction-types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionTypePublicData updateTransactionType(@PathVariable UUID id, @Valid @RequestBody TransactionTypeUpdateData dados) throws TransactionTypeNotFoundException {
        return new TransactionTypePublicData(updateTransactionTypeUseCase.execute(id, dados));
    }
}
