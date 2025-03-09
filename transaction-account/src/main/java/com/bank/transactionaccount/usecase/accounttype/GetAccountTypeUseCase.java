package com.bank.transactionaccount.usecase.accounttype;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;

import java.util.UUID;

public class GetAccountTypeUseCase {

    private final AccountTypeGateway accountTypeGateway;

    public GetAccountTypeUseCase(AccountTypeGateway accountTypeGateway) {
        this.accountTypeGateway = accountTypeGateway;
    }

    public AccountType execute(UUID id) throws AccountTypeNotFoundException {
        return this.accountTypeGateway
                .findById(id)
                .orElseThrow(AccountTypeNotFoundException::new);
    }
}
