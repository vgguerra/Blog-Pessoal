package com.blogpessoal.controllers;

import com.blogpessoal.configs.security.TokenService;
import com.blogpessoal.dtos.AuthenticationDTO;
import com.blogpessoal.dtos.LoginDTO;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) throws Exception {
        boolean authenticated = authenticationService.authenticateUser(authenticationDTO.email(), authenticationDTO.password());
        if(userService.findUserByEmail(authenticationDTO.email()) == null) throw new Exception("User not found!");

        var token = tokenService.generateToken(this.userService.findUserByEmail(authenticationDTO.email()));

        if(authenticated) return ResponseEntity.ok("Login successful. Your token is: " + new LoginDTO(token));
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) throws Exception {
        this.userService.createUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

}
