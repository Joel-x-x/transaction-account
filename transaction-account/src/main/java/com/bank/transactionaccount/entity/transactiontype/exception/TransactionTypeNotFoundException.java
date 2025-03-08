package com.bank.transactionaccount.entity.transactiontype.exception;

public class TransactionTypeNotFoundException extends Exception {
    public TransactionTypeNotFoundException() {
        super("Tipo de transacción no encontrado");
    }
}
