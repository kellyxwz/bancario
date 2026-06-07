package com.sistema.bancario.DTO;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        Long phone
) {
}
