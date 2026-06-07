package com.sistema.bancario.repository;

import com.sistema.bancario.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountService extends JpaRepository<Account, Long> {
}
