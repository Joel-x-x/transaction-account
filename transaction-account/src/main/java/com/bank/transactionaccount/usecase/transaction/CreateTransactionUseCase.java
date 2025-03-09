package com.bank.transactionaccount.usecase.transaction;

import com.bank.transactionaccount.entity.account.exception.AccountNotFoundException;
import com.bank.transactionaccount.entity.account.gateway.AccountGateway;
import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.entity.transaction.gateway.TransactionGateway;
import com.bank.transactionaccount.entity.transaction.model.Transaction;
import com.bank.transactionaccount.entity.transactiontype.exception.TransactionTypeNotFoundException;
import com.bank.transactionaccount.entity.transactiontype.gateway.TransactionTypeGateway;
import com.bank.transactionaccount.entity.transactiontype.model.TransactionType;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionRegistrationData;
import com.bank.transactionaccount.usecase.transaction.rules.TransactionBusinessRuleValidator;

import java.util.List;

import static java.time.LocalDate.now;

public class CreateTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final TransactionTypeGateway transactionTypeGateway;
    private final AccountGateway accountGateway;
    private final List<TransactionBusinessRuleValidator> businessRuleValidators;

    public CreateTransactionUseCase(TransactionGateway transactionGateway,
                                    TransactionTypeGateway transactionTypeGateway,
                                    AccountGateway accountGateway,
                                    List<TransactionBusinessRuleValidator> businessRuleValidators) {
        this.transactionGateway = transactionGateway;
        this.transactionTypeGateway = transactionTypeGateway;
        this.accountGateway = accountGateway;
        this.businessRuleValidators = businessRuleValidators;
    }

    public Transaction execute(ITransactionRegistrationData dados) throws TransactionTypeNotFoundException,
            AccountNotFoundException,
            TransactionBusinessRuleViolationException {

        TransactionType transactionType = transactionTypeGateway.findById(dados.transactionTypeId())
                .orElseThrow(TransactionTypeNotFoundException::new);

        Account account = accountGateway.findById(dados.accountId())
                .orElseThrow(AccountNotFoundException::new);

        // Business rules validations
        // TODO: WE CAN ADD MORE BUSINESS RULES
        for (TransactionBusinessRuleValidator validator : businessRuleValidators) {
            validator.validate(account, dados);
        }

        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .amount(dados.amount())
                .balance(account.getInitialBalance())
                .account(account)
                .date(now())
                .isCorrective(Boolean.TRUE.equals(dados.isCorrective()))
                .build();

        // Update balance account
        account.setInitialBalance(account.getInitialBalance().add(dados.amount()));

        this.accountGateway.update(account);

        return this.transactionGateway.create(transaction);
    }
}
