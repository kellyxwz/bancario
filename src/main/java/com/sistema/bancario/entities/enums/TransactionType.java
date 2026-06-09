package com.sistema.bancario.entities.enums;

import java.math.BigDecimal;

public enum TransactionType {

    DEPOSIT("deposit"),
    TRANSFER("user"),
    WITHDRAWAL("withdrawl");

    private String transaction;

    TransactionType(String transaction) {
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }
}
