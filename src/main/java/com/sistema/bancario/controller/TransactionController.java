package com.sistema.bancario.controller;

import com.sistema.bancario.DTO.Request.RequestTransactionDTO;
import com.sistema.bancario.DTO.Response.AccountResponseDTO;
import com.sistema.bancario.DTO.Response.TransactionResponseDTO;
import com.sistema.bancario.entities.Account;
import com.sistema.bancario.entities.Transaction;
import com.sistema.bancario.entities.enums.TransactionType;
import com.sistema.bancario.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    public final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/numberAccount")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody RequestTransactionDTO dto, @PathVariable String numberAccount){
        TransactionResponseDTO transaction = service.createTransaction(dto, numberAccount);

        return ResponseEntity.ok(transaction);
    }

}
