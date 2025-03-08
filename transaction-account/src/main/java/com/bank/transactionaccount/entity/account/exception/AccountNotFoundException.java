package com.bank.transactionaccount.entity.account.exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException() {
        super("Cuenta no encontrada");
    }
}
