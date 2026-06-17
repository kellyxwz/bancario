package com.sistema.bancario.DTO.Request;

import com.sistema.bancario.entities.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RequestTransactionDTO(

        @NotBlank(message = "The transaction amount is required")
        @Positive(message = "The transaction value must be greater than zero")
        BigDecimal value,

        @NotBlank(message = "The transaction type is required")
        TransactionType type
)
{
}
