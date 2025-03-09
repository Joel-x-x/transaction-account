package com.bank.transactionaccount.infrastructure.transactiontype.controller;

import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypeRegistrationData;
import com.bank.transactionaccount.usecase.transactiontype.CreateTransactionTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateTransactionTypeController {

    private final CreateTransactionTypeUseCase createTransactionTypeUseCase;

    public CreateTransactionTypeController(CreateTransactionTypeUseCase createTransactionTypeUseCase) {
        this.createTransactionTypeUseCase = createTransactionTypeUseCase;
    }

    @PostMapping("${api.prefix}/transaction-types")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionTypePublicData createCustomer(@Valid @RequestBody TransactionTypeRegistrationData dados) {
        return new TransactionTypePublicData(createTransactionTypeUseCase.execute(dados));
    }


}
