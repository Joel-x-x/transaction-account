package com.bank.transactionaccount.usecase.accounttype;

import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.usecase.accounttype.dto.IAccountTypeRegistrationData;
import com.bank.transactionaccount.usecase.transactiontype.dto.ITransactionTypeRegistrationData;

public class CreateAccountTypeUseCase {

    private final AccountTypeGateway accountTypeGateway;

    public CreateAccountTypeUseCase(AccountTypeGateway accountTypeGateway) {
        this.accountTypeGateway = accountTypeGateway;
    }

    public AccountType execute(IAccountTypeRegistrationData dados) {
        AccountType accountType = AccountType.builder()
                .name(dados.name())
                .description(dados.description())
                .build();

        return this.accountTypeGateway.create(accountType);
    }
}
