package com.blogpessoal.services;

import com.blogpessoal.models.Users;
import com.blogpessoal.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username);
    }

    public boolean authenticateUser(String email, String password) {
        Users user = (Users) usersRepository.findByEmail(email);
        if (user != null) return passwordEncoder.matches(password, user.getPassword());
        return false;
    }

}
