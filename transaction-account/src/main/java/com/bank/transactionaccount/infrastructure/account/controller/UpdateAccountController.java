package com.bank.transactionaccount.infrastructure.account.controller;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.infrastructure.account.dto.AccountPublicData;
import com.bank.transactionaccount.infrastructure.account.dto.AccountUpdateData;
import com.bank.transactionaccount.usecase.account.UpdateAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateAccountController {

    private final UpdateAccountUseCase updateAccountUseCase;

    public UpdateAccountController(UpdateAccountUseCase updateAccountUseCase) {
        this.updateAccountUseCase = updateAccountUseCase;
    }

    @PutMapping("${api.prefix}/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountPublicData updateAccount(@PathVariable UUID id, @Valid @RequestBody AccountUpdateData dados) throws AccountNotFoundException {
        return new AccountPublicData(updateAccountUseCase.execute(id, dados));
    }
}
