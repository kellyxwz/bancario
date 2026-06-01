package com.sistema.bancario.service;

import com.sistema.bancario.entities.User;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(long id){
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    public void delete(long id){
        repository.delete(id);
    }

}
