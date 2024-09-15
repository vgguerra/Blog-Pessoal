package com.blogpessoal.repositories;

import com.blogpessoal.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersById(Long id);

    Optional<Users> findUsersByEmail(String email);

    UserDetails findByEmail(String email);
}
