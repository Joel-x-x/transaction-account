package com.bank.transactionaccount.usecase.accounttype;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypeUpdateData;

import java.util.UUID;

public class UpdateAccountTypeUseCase {

    private final AccountTypeGateway accountTypeGateway;

    public UpdateAccountTypeUseCase(AccountTypeGateway accountTypeGateway) {
        this.accountTypeGateway = accountTypeGateway;
    }

    public AccountType execute(UUID id, IAccountTypeUpdateData dados) throws AccountTypeNotFoundException {
        AccountType accountType = this.accountTypeGateway.findById(id)
                .orElseThrow(AccountTypeNotFoundException::new);

        if(!dados.name().isBlank())
            accountType.setName(dados.name());
        if(!dados.description().isBlank())
            accountType.setDescription(dados.description());

        return this.accountTypeGateway.update(accountType);

    }
}
