package com.blogpessoal.controllers;

import com.blogpessoal.dtos.AuthenticationDTO;
import com.blogpessoal.dtos.UserDTO;
import com.blogpessoal.models.Users;
import com.blogpessoal.services.AuthenticationService;
import com.blogpessoal.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        boolean authenticated = authenticationService.authenticateUser(authenticationDTO.email(), authenticationDTO.password());
        if(authenticated) return ResponseEntity.ok("Login successful");
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) throws Exception {
        this.userService.createUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

}
