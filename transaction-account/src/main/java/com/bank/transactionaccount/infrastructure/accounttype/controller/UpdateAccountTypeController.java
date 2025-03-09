package com.bank.transactionaccount.infrastructure.accounttype.controller;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypeUpdateData;
import com.bank.transactionaccount.usecase.accounttype.UpdateAccountTypeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateAccountTypeController {

    private final UpdateAccountTypeUseCase updateAccountTypeUseCase;

    public UpdateAccountTypeController(UpdateAccountTypeUseCase updateAccountTypeUseCase) {
        this.updateAccountTypeUseCase = updateAccountTypeUseCase;
    }

    @PutMapping("${api.prefix}/account-types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountTypePublicData updateAccountType(@PathVariable UUID id, @Valid @RequestBody AccountTypeUpdateData dados) throws AccountTypeNotFoundException {
        return new AccountTypePublicData(updateAccountTypeUseCase.execute(id, dados));
    }
}
