package com.sistema.bancario.DTO.Response;

import com.sistema.bancario.entities.User;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        Long phone
) {

    public ResponseUserDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }


}
