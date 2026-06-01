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
        return obj.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

        public void deleteById(long id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar. Usuário não encontrado com o ID: " + id);
        }
        try {
            repository.deleteById(id);
        }catch (RuntimeException e ){
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public User insert(User user) {
        try {
            return repository.save(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao inserir o usuário: " + e.getMessage());
        }
    }

    public User update(long id, User updateDate){
        try {
            User user = repository.findById(id).orElseThrow(() -> new RuntimeException("usuário não encontrado"));

            updateDate(user, updateDate);

            return repository.save(user);
        }catch (RuntimeException e){
            throw new RuntimeException("erro ao atualizar o usuário"+e.getMessage());
        }
    }

    public void updateDate(User user, User newUser){
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
    }

}
