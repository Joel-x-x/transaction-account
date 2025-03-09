package com.bank.transactionaccount.usecase.accounttype;

import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;

import java.util.UUID;

import static java.time.LocalDateTime.now;

public class DeleteAccountTypeUseCase {

    private final AccountTypeGateway accountTypeGateway;

    public DeleteAccountTypeUseCase(AccountTypeGateway accountTypeGateway) {
        this.accountTypeGateway = accountTypeGateway;
    }

    public AccountType execute(UUID id) throws AccountTypeNotFoundException {
        AccountType accountType = this.accountTypeGateway.findById(id)
                .orElseThrow(AccountTypeNotFoundException::new);

        accountType.setDeleted(true);
        accountType.setDeletedAt(now());

        this.accountTypeGateway.update(accountType);

        return accountType;
    }
}
