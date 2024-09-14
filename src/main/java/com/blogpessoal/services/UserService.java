package com.blogpessoal.services;

import com.blogpessoal.dtos.UserDTO;
import com.blogpessoal.models.Users;
import com.blogpessoal.models.enums.Roles;
import com.blogpessoal.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public void validatePublication(Users user) throws Exception {
        if(user.getRole() == Roles.USER) {
            throw new Exception("User don't have permission to perform this action");
        }
    }

    public void validateAdmin(Users user) throws Exception {
        if(user.getRole() != Roles.ADMIN){
            throw new Exception("User don't have permission to perform this action");
        }
    }

    public Users createUser(UserDTO user) {

        Users newUser = new Users(user);
        newUser.setRole(Roles.ADMIN);

//        String passwordEnconde = new BCryptPasswordEncoder().encode(newUser.getPassword());
//        newUser.setPassword(passwordEncode);
        this.usersRepository.save(newUser);
        return newUser;
    }

    public List<Users> findAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users findByID(Long id) throws Exception {
        return this.usersRepository.findUsersById(id).orElseThrow(() ->new Exception("User not found"));
    }


}
