package com.sistema.bancario.service;

import com.sistema.bancario.DTO.Request.RequestUserDTO;
import com.sistema.bancario.DTO.Response.ResponseUserDTO;
import com.sistema.bancario.entities.User;
import com.sistema.bancario.exceptions.ResourceNotFoundException;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseUserDTO findById(long id){
        User user = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id));
        return new ResponseUserDTO(user);
    }

        public void deleteById(long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public ResponseUserDTO insert(RequestUserDTO dto) {
        try {
            User user = toEntity(dto);
            User userSave = repository.save(user);

            return new ResponseUserDTO(userSave);

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao inserir o usuário: " + e.getMessage());
        }
    }

    public ResponseUserDTO update(long id, RequestUserDTO updateDate){
        try {
            User user = repository.findById(id).orElseThrow(() -> new RuntimeException("usuário não encontrado"));

            updateData(user, updateDate);

            repository.save(user);

            return new ResponseUserDTO(user);
        }catch (RuntimeException e){
            throw new RuntimeException("erro ao atualizar o usuário"+ e.getMessage());
        }
    }

    private void updateData(User user, RequestUserDTO newUser){
        user.setName(newUser.name());
        user.setEmail(newUser.email());
        user.setPhone(newUser.phone());
        user.setPassword(newUser.password());
    }


    private User toEntity(RequestUserDTO obj){
        User user = new User();

        user.setName(obj.name());
        user.setEmail(obj.email());
        user.setPhone(obj.phone());
        user.setPassword(obj.password());

        return user;
    }



}
