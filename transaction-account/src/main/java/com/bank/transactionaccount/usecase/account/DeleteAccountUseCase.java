package com.bank.transactionaccount.usecase.account;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;

import java.util.UUID;

import static java.time.LocalDateTime.now;

public class DeleteAccountUseCase {

    private final AccountGateway accountGateway;

    public DeleteAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(UUID id) throws AccountNotFoundException {
        Account account = this.accountGateway.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        account.setDeleted(true);
        account.setDeletedAt(now());

        this.accountGateway.update(account);

        return account;
    }
}
