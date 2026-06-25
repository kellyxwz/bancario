package com.sistema.bancario.DTO.Response;

import com.sistema.bancario.entities.Account;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        BigDecimal balance,
        String numberAccount
) {

    public AccountResponseDTO(Account account) {
        this(
                account.getId(),
                account.getBalance(),
                account.getNumberAccount()
        );
    }
}
