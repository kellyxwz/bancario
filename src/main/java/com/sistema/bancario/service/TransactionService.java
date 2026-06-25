package com.sistema.bancario.service;

import com.sistema.bancario.DTO.Request.RequestTransactionDTO;
import com.sistema.bancario.DTO.Response.TransactionResponseDTO;
import com.sistema.bancario.entities.Account;
import com.sistema.bancario.entities.Transaction;
import com.sistema.bancario.entities.enums.TransactionType;
import com.sistema.bancario.repository.AccountRepository;
import com.sistema.bancario.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Transactional
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionResponseDTO deposit(RequestTransactionDTO dto, Account account){
        BigDecimal amount = account.getBalance().add(dto.value());
        account.setBalance(amount);

        Transaction transaction = toEntity(
                dto.value(),
                TransactionType.DEPOSIT,
                account
        );

        accountRepository.save(account);
        transactionRepository.save(transaction);


        return new TransactionResponseDTO(transaction);
    }


    public TransactionResponseDTO withdraw(RequestTransactionDTO dto, Account account){

        if (account.getBalance().compareTo(dto.value())<0){
            throw new RuntimeException("Unavailable funds");
        }

        BigDecimal amount = account.getBalance().subtract(dto.value());
        account.setBalance(amount);

        Transaction transaction = toEntity(
                dto.value(),
                TransactionType.WITHDRAWAL,
                account
        );

        accountRepository.save(account);
        transactionRepository.save(transaction);

        return new TransactionResponseDTO(transaction);
    }


    public TransactionResponseDTO transfer(RequestTransactionDTO dto, Account originAccount, String  destinationAccountNumber){

        Account destinationAccount = accountRepository.findByNumberAccount(destinationAccountNumber);

        if (destinationAccount == null){
            throw  new RuntimeException("Destiny account not found");
        }

        if (originAccount.getNumberAccount().equals(destinationAccountNumber)) {
            throw new RuntimeException("Cannot transfer to the same account");
        }

        if (originAccount.getBalance().compareTo(dto.value())< 0){
            throw new RuntimeException("Unavailable funds");
        }

        BigDecimal amount = originAccount.getBalance().subtract(dto.value());
        originAccount.setBalance(amount);

        BigDecimal destinationBalance = destinationAccount.getBalance().add(dto.value());
        destinationAccount.setBalance(destinationBalance);

        Transaction transaction = toEntity(
                dto.value(),
                TransactionType.TRANSFER,
                originAccount
        );

        accountRepository.save(destinationAccount);
        accountRepository.save(originAccount);
        transactionRepository.save(transaction);


        return new TransactionResponseDTO(transaction);
    }


    public TransactionResponseDTO createTransaction(RequestTransactionDTO dto, String numberAccount){
        Account account = accountRepository.findByNumberAccount(numberAccount);

        if (!accountRepository.existsByNumberAccount(numberAccount)){
            throw new RuntimeException("account not found");
        }

        switch (dto.type()){
            case DEPOSIT -> {
                return deposit(dto, account);
            }
            case WITHDRAWAL -> {
                return withdraw(dto, account);
            }
            case TRANSFER -> {
                return transfer(dto, account, dto.destinationAccountNumber());
            }
        }
        throw new IllegalArgumentException("invalid type");
    }

    private Transaction toEntity ( BigDecimal value, TransactionType type, Account account) {

        Transaction transaction = new Transaction();
        transaction.setValue(value);
        transaction.setTransactionType(type);
        transaction.setCreatedAt(Instant.now());
        transaction.setAccount(account);

        return transaction;
    }

}
