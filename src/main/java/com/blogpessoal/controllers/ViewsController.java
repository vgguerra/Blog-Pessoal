package com.blogpessoal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewsController {

    @GetMapping("/auth/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Email ou senha inválido");
        }
        return "login";  // Retorna a página de login
    }

    @GetMapping("/auth/register")
    public String registerUser() {
        return "register";
    }
}
