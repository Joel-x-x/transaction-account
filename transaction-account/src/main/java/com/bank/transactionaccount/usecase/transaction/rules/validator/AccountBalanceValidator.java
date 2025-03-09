package com.bank.transactionaccount.usecase.transaction.rules.validator;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionRegistrationData;
import com.bank.transactionaccount.usecase.transaction.rules.TransactionBusinessRuleValidator;

import java.math.BigDecimal;

public class AccountBalanceValidator implements TransactionBusinessRuleValidator {
    @Override
    public void validate(Account account, ITransactionRegistrationData dados) throws TransactionBusinessRuleViolationException {
        if(account.getInitialBalance().compareTo(dados.amount()) < 0 && dados.amount().compareTo(BigDecimal.ZERO) < 0)
            throw new TransactionBusinessRuleViolationException("Saldo no disponible");
    }
}
