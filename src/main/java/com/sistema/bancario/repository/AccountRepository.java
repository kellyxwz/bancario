package com.sistema.bancario.repository;

import com.sistema.bancario.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByNumberAccount(String numberAccount);

    Account findByNumberAccount(String numberAccount);

}
