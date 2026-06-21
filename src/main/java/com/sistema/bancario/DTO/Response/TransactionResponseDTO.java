package com.sistema.bancario.DTO.Response;

import com.sistema.bancario.entities.Transaction;
import com.sistema.bancario.entities.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionResponseDTO(
        Long id,
        BigDecimal value,
        TransactionType type,
        Instant createdAt,
        String accountNumber,
        BigDecimal currentBalance
) {
    public TransactionResponseDTO(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getValue(),
                transaction.getTransactionType(),
                transaction.getCreatedAt(),
                transaction.getAccount().getNumberAccount(),
                transaction.getAccount().getBalance()
        );
    }
}