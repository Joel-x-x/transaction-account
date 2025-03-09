package com.bank.transactionaccount.infrastructure.accounttype.controller;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.infrastructure.transactiontype.dto.TransactionTypePublicData;
import com.bank.transactionaccount.usecase.accounttype.CreateAccountTypeUseCase;
import com.bank.transactionaccount.usecase.accounttype.DeleteAccountTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteAccountTypeController {

    private final DeleteAccountTypeUseCase deleteAccountTypeUseCase;

    public DeleteAccountTypeController(DeleteAccountTypeUseCase deleteAccountTypeUseCase) {
        this.deleteAccountTypeUseCase = deleteAccountTypeUseCase;
    }

    @DeleteMapping("${api.prefix}/account-types/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountTypePublicData deleteCustomerType(@PathVariable UUID id) throws AccountTypeNotFoundException {
        return new AccountTypePublicData(deleteAccountTypeUseCase.execute(id));
    }
}
