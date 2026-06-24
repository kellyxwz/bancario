package com.sistema.bancario.DTO;

public record RegisterDTO(
        String name,
        Long phone,
        String email,
        String password
) {
}
