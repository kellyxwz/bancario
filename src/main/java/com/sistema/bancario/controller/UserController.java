package com.sistema.bancario.controller;

import com.sistema.bancario.entities.User;
import com.sistema.bancario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id){
        User user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<User> insert(@RequestBody User user){
        User entity = service.insert(user);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> upadate(@PathVariable long id, @RequestBody User user){
        User entity = service.update(id, user);
        return ResponseEntity.ok().body(entity);
    }

}
