package com.sistema.bancario.DTO;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        BigDecimal saldo,
        String numeroConta
) {
}
