package com.bank.transactionaccount.entity.transaction.exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException() {
        super("Movimiento no encontrado");
    }
}
