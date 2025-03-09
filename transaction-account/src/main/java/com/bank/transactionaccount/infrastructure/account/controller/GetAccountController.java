package com.bank.transactionaccount.infrastructure.account.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.infrastructure.account.dto.AccountPublicData;
import com.bank.transactionaccount.usecase.account.GetAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetAccountController {

    private final GetAccountUseCase getAccountUseCase;

    public GetAccountController(GetAccountUseCase getAccountUseCase) {
        this.getAccountUseCase = getAccountUseCase;
    }

    @GetMapping("${api.prefix}/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPublicData getAccount(@PathVariable UUID id) throws AccountNotFoundException {
        return new AccountPublicData(getAccountUseCase.execute(id));
    }
}
