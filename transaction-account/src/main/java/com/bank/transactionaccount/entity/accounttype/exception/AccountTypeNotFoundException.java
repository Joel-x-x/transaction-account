package com.bank.transactionaccount.entity.accounttype.exception;

public class AccountTypeNotFoundException extends Exception {
    public AccountTypeNotFoundException() {
        super("Tipo de cuenta no encontrado");
    }
}
