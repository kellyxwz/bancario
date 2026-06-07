package com.sistema.bancario.DTO;

public record RequestUserDTO(
        String name,
        String email,
        Long phone,
        String password
) {
}
