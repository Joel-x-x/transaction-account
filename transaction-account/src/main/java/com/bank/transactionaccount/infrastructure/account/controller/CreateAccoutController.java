package com.bank.transactionaccount.infrastructure.account.controller;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.account.dto.AccountPublicData;
import com.bank.transactionaccount.infrastructure.account.dto.AccountRegistrationData;
import com.bank.transactionaccount.usecase.account.CreateAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccoutController {

    private final CreateAccountUseCase createAccountUseCase;

    public CreateAccoutController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping("${api.prefix}/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountPublicData createCustomer(@Valid @RequestBody AccountRegistrationData dados) throws AccountTypeNotFoundException {
        return new AccountPublicData(createAccountUseCase.execute(dados));
    }
}
