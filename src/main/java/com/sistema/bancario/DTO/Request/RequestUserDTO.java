package com.sistema.bancario.DTO.Request;

public record RequestUserDTO(
        String name,
        String email,
        Long phone,
        String password
) {
}
