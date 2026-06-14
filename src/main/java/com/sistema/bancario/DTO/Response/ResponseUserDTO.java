package com.sistema.bancario.DTO.Response;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        Long phone
) {
}
