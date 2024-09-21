package com.blogpessoal.services;

import com.blogpessoal.dtos.UserDTO;
import com.blogpessoal.models.Users;
import com.blogpessoal.models.enums.Roles;
import com.blogpessoal.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validatePublication(Users user) throws Exception {
        System.out.println(List.of(user.getRole()));
        if(user.getRole() == Roles.USER) {
            throw new Exception("User don't have permission to perform this action");
        }
    }

    public void validateAdmin(Users user) throws Exception {
        if(user.getRole() != Roles.ADMIN){
            throw new Exception("User don't have permission to perform this action");
        }
    }

    public Users createUser(UserDTO user) throws Exception {

        Users newUser = new Users(user);

        if(this.usersRepository.findByEmail(user.email()) != null){
            throw new Exception("User already exists");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.usersRepository.save(newUser);
        return newUser;
    }

    public List<Users> findAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users findUserByEmail(String email) {
        return (Users) this.usersRepository.findByEmail(email);
    }

    public Users findByID(Long id) throws Exception {
        return this.usersRepository.findUsersById(id).orElseThrow(() ->new Exception("User not found"));
    }


}
