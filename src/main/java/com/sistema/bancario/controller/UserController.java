package com.sistema.bancario.controller;

import com.sistema.bancario.DTO.RequestUserDTO;
import com.sistema.bancario.DTO.ResponseUserDTO;
import com.sistema.bancario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> findAll(){
        List<ResponseUserDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable long id){
        ResponseUserDTO user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ResponseUserDTO> insert(@RequestBody RequestUserDTO user){
        ResponseUserDTO entity = service.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.id()).toUri();

        return ResponseEntity.created(uri).body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> upadate(@PathVariable long id, @RequestBody RequestUserDTO user){
        ResponseUserDTO entity = service.update(id, user);
        return ResponseEntity.ok().body(entity);
    }

}
