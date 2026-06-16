package com.sistema.bancario.DTO.Request;

import com.sistema.bancario.entities.enums.TransactionType;

import java.math.BigDecimal;

public record RequestTransactionDTO(
        BigDecimal value,
        TransactionType type
)
{
}
