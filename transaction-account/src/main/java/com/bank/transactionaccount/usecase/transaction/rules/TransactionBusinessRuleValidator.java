package com.bank.transactionaccount.usecase.transaction.rules;

import com.bank.transactionaccount.entity.account.model.Account;
import com.bank.transactionaccount.entity.transaction.exception.TransactionBusinessRuleViolationException;
import com.bank.transactionaccount.usecase.transaction.dto.ITransactionRegistrationData;

public interface TransactionBusinessRuleValidator {
    void validate(Account account, ITransactionRegistrationData dados) throws TransactionBusinessRuleViolationException;
}
