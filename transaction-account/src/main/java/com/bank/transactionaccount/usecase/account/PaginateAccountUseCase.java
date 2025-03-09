package com.bank.transactionaccount.usecase.account;

import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class PaginateAccountUseCase {

    private final AccountGateway accountGateway;

    public PaginateAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Page<Account> execute(int page, int size, String customerId) {
        Pageable pageable = PageRequest.of(page, size);

        return accountGateway.findAllExcludingDeleted(pageable, customerId);
    }
}
