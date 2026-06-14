package com.sistema.bancario.entities.enums;

public enum TransactionType {

    DEPOSIT("Depósito"),
    WITHDRAWAL("Saque"),
    TRANSFER("Transferência");

    private String transaction;

    TransactionType(String transaction) {
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }
}
