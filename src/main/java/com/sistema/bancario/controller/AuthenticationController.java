package com.sistema.bancario.controller;

import com.sistema.bancario.DTO.AuthenticationDTO;
import com.sistema.bancario.DTO.LoginResponseDTO;
import com.sistema.bancario.DTO.RegisterDTO;
import com.sistema.bancario.entities.User;
import com.sistema.bancario.repository.UserRepository;
import com.sistema.bancario.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserRepository repository;
    private final TokenService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserRepository repository, TokenService service, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.service = service;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var username = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(username);

        var token = service.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findByEmail(data.login()) != null ){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
