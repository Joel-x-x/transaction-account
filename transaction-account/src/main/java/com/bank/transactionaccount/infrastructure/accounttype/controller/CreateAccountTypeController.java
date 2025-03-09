package com.bank.transactionaccount.infrastructure.accounttype.controller;

import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypeRegistrationData;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypeRegistrationData;
import com.bank.transactionaccount.usecase.accounttype.CreateAccountTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountTypeController {

    private final CreateAccountTypeUseCase createAccountTypeUseCase;

    public CreateAccountTypeController(CreateAccountTypeUseCase createAccountTypeUseCase) {
        this.createAccountTypeUseCase = createAccountTypeUseCase;
    }

    @PostMapping("${api.prefix}/account-types")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountTypePublicData createCustomer(@Valid @RequestBody AccountTypeRegistrationData dados) {
        return new AccountTypePublicData(createAccountTypeUseCase.execute(dados));
    }
}
