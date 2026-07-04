package com.sistema.bancario.service;

import com.sistema.bancario.DTO.Request.RequestUserDTO;
import com.sistema.bancario.DTO.Response.ResponseUserDTO;
import com.sistema.bancario.entities.User;
import com.sistema.bancario.exceptions.DatabaseException;
import com.sistema.bancario.exceptions.ResourceNotFoundException;
import com.sistema.bancario.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseUserDTO findById(long id){
        User user = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(id));
        return new ResponseUserDTO(user);
    }

    public void deleteById(long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }

        try {
            repository.deleteById(id);

        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possivel deletar usuário");
        }
    }

    public ResponseUserDTO insert(RequestUserDTO dto) {
            User user = toEntity(dto);

        try {
            user = repository.save(user);

            return new ResponseUserDTO(user);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível inserir o usuário ");
        }
    }

    public ResponseUserDTO update(long id, RequestUserDTO updateDate){
            User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

            updateData(user, updateDate);

        try {
            repository.save(user);

            return new ResponseUserDTO(user);

        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("erro ao atualizar o usuário");
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
