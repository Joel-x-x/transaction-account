package com.bank.transactionaccount.usecase.account;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.usecase.account.dto.IAccountUpdateData;

import java.util.UUID;

public class UpdateAccountUseCase {

    private final AccountGateway accountGateway;

    public UpdateAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(UUID id, IAccountUpdateData dados) throws AccountNotFoundException {
        Account account = this.accountGateway.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        if(!dados.alias().isBlank()) account.setAlias(dados.alias());

        return this.accountGateway.update(account);
    }

}
