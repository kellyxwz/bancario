package com.sistema.bancario.repository;

import com.sistema.bancario.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
