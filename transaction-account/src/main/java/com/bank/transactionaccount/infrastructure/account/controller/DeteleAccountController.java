package com.bank.transactionaccount.infrastructure.account.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.infrastructure.account.dto.AccountPublicData;
import com.bank.transactionaccount.usecase.account.DeleteAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeteleAccountController {

    private final DeleteAccountUseCase deleteAccountUseCase;

    public DeteleAccountController(DeleteAccountUseCase deleteAccountUseCase) {
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    @DeleteMapping("${api.prefix}/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPublicData deleteCustomer(@PathVariable UUID id) throws AccountNotFoundException {
        return new AccountPublicData(deleteAccountUseCase.execute(id));
    }
}
