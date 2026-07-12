package com.sistema.bancario.service;

import com.sistema.bancario.DTO.Response.AccountResponseDTO;
import com.sistema.bancario.entities.Account;
import com.sistema.bancario.entities.User;
import com.sistema.bancario.exceptions.DatabaseException;
import com.sistema.bancario.exceptions.ResourceNotFoundException;
import com.sistema.bancario.repository.AccountRepository;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountResponseDTO createAccount (long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        Account account = new Account();

        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setNumberAccount(generateAccountNumber());

        try {
            Account savedAccount = accountRepository.save(account);
            return new AccountResponseDTO(savedAccount);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro ao criar conta.");
        }

    }

    public AccountResponseDTO findById(long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return new AccountResponseDTO(account);
    }

    private String generateAccountNumber() {

        String number;

        do {
            number = String.valueOf(
                    ThreadLocalRandom.current()
                            .nextLong(100000, 999999)
            );
        } while (accountRepository.existsByNumberAccount(number));

        return number;
    }



}
