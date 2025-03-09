package com.bank.transactionaccount.usecase.account;

import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.accounttype.exception.AccountTypeNotFoundException;
import com.bank.transactionaccount.entity.accounttype.gateway.AccountTypeGateway;
import com.bank.transactionaccount.entity.accounttype.model.AccountType;
import com.bank.transactionaccount.usecase.account.dto.IAccountRegistrationData;

import java.math.BigDecimal;
import java.util.Random;

import static java.time.LocalDate.now;

public class CreateAccountUseCase {

    private final AccountGateway accountGateway;
    private final AccountTypeGateway accountTypeGateway;

    public CreateAccountUseCase(AccountGateway accountGateway, AccountTypeGateway accountTypeGateway) {
        this.accountGateway = accountGateway;
        this.accountTypeGateway = accountTypeGateway;
    }

    public Account execute(IAccountRegistrationData dados) throws AccountTypeNotFoundException{
        AccountType accountType = accountTypeGateway.findById(dados.accountTypeId())
                .orElseThrow(AccountTypeNotFoundException::new);

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .alias(dados.alias())
                .accountType(accountType)
                .initialBalance(dados.initialBalance())
                .customerId(dados.customerId())
                .build();

        return this.accountGateway.create(account);
    }

    public static String generateAccountNumber() {
        String year = String.valueOf(now().getYear()).substring(2, 4);
        int randomPart = new Random().nextInt(900000) + 100000;
        return year + randomPart;
    }

}
