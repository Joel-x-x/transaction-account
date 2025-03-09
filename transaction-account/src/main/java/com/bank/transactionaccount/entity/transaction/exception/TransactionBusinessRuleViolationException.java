package com.bank.transactionaccount.entity.transaction.exception;

public class TransactionBusinessRuleViolationException extends Exception{
    public TransactionBusinessRuleViolationException(String s) {
        super(s);
    }
}
