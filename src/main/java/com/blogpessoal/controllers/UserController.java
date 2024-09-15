package com.blogpessoal.controllers;

import com.blogpessoal.dtos.UserDTO;
import com.blogpessoal.models.Users;
import com.blogpessoal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDTO userDTO) throws Exception {
        Users newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users = this.userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) throws Exception {
        Users user = this.userService.findByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
