package com.sistema.bancario.controller;

import com.sistema.bancario.DTO.AccountResponseDTO;
import com.sistema.bancario.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable long id){
        AccountResponseDTO newConta = service.createAccount(id);

        return ResponseEntity.ok(newConta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable long id){
        AccountResponseDTO account = service.findById(id);
        return ResponseEntity.ok(account);
    }



}
