package com.bank.transactionaccount.infrastructure.accounttype.controller;

import com.bank.transactionaccount.infrastructure.accounttype.dto.AccountTypePublicData;
import com.bank.transactionaccount.usecase.accounttype.ListAccountTypeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListAccountTypeController {

    private final ListAccountTypeUseCase listAccountTypeUseCase;

    public ListAccountTypeController(ListAccountTypeUseCase listAccountTypeUseCase) {
        this.listAccountTypeUseCase = listAccountTypeUseCase;
    }

    @GetMapping("${api.prefix}/account-types")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountTypePublicData> listAccountTypes() {
        return this.listAccountTypeUseCase.execute().stream().map(AccountTypePublicData::new).toList();
    }
}
