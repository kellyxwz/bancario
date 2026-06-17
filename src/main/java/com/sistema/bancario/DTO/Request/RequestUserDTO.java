package com.sistema.bancario.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserDTO(
        @NotBlank(message = "Name is required") String name,

        @NotBlank(message = "Email is required")
        @Email( message = "Email invalid")
        String email,

        Long phone,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "The password must have minimum 6 characters")
        String password
) {
}
