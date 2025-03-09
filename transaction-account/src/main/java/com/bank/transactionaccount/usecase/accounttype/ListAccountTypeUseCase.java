package com.bank.transactionaccount.usecase.accounttype;

import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;

import java.util.List;

public class ListAccountTypeUseCase {

    private final AccountTypeGateway accountTypeGateway;

    public ListAccountTypeUseCase(AccountTypeGateway accountTypeGateway) {
        this.accountTypeGateway = accountTypeGateway;
    }

    public List<AccountType> execute() {
        return this.accountTypeGateway.findAll();
    }
}
