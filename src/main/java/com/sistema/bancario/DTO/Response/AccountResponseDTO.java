package com.sistema.bancario.DTO.Response;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        BigDecimal balance,
        String numeroConta
) {


}
