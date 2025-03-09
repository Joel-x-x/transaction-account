package com.bank.transactionaccount.usecase.account;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;

import java.util.UUID;

public class GetAccountUseCase {

    private final AccountGateway accountGateway;

    public GetAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(UUID id) throws AccountNotFoundException {
        return this.accountGateway
                .findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }
}
