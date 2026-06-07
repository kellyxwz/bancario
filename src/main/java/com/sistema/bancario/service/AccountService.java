package com.sistema.bancario.service;

import com.sistema.bancario.DTO.AccountResponseDTO;
import com.sistema.bancario.entities.Account;
import com.sistema.bancario.entities.User;
import com.sistema.bancario.repository.AccountRepository;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountResponseDTO createAccount (long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("usuário não encontrado"));
        Account account = new Account();

        account.setUser(user);
        account.setSaldo(BigDecimal.ZERO);
        account.setNumeroConta(generateAccountNumber());

        Account savedAccount = accountRepository.save(account);

        return new AccountResponseDTO(
                savedAccount.getId(),
                savedAccount.getSaldo(),
                savedAccount.getNumeroConta()
        );
    }

    public AccountResponseDTO findById(long id){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("conta não encontrado"));

        return new AccountResponseDTO(
                account.getId(),
                account.getSaldo(),
                account.getNumeroConta()
        );
    }

    private String generateAccountNumber() {

        String number;

        do {
            number = String.valueOf(
                    ThreadLocalRandom.current()
                            .nextLong(100000, 999999)
            );
        } while (accountRepository.existsByNumeroConta(number));

        return number;
    }



}
