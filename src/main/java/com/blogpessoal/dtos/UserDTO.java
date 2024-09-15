package com.blogpessoal.dtos;

import com.blogpessoal.models.enums.Roles;

public record UserDTO(String username, String password, String email, String phone, Roles role) {
}
