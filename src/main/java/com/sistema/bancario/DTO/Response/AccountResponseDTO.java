package com.sistema.bancario.DTO.Response;

import com.sistema.bancario.entities.Account;

import java.math.BigDecimal;

public record AccountResponseDTO(
        Long id,
        BigDecimal balance,
        String numeroConta
) {

    public AccountResponseDTO(Account account) {
        this(
                account.getId(),
                account.getBalance(),
                account.getNumberAccount()
        );
    }
}
