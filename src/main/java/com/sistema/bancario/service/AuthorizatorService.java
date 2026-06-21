package com.sistema.bancario.service;

import com.sistema.bancario.entities.User;
import com.sistema.bancario.infra.security.UserDetailsImpl;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizatorService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public AuthorizatorService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userEntity = repository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new UserDetailsImpl(userEntity);
    }


}
