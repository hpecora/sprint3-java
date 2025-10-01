package com.mottu.mototracker.controller;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private final JdbcUserDetailsManager users;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JdbcUserDetailsManager users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }



    // LOGIN (página) - o POST /login é tratado automaticamente pelo Spring Security
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";     // templates/auth/login.html
    }



    // REGISTER (página)
    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";  // templates/auth/register.html
    }



    // REGISTER (envio do formulário)
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false, defaultValue = "ROLE_USER") String role
    ) {

        // validações simples
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            return "redirect:/register?error=empty";
        }

        if (users.userExists(username)) {
            // usuário já existe
            return "redirect:/register?error=exists";
        }

        // garante prefixo ROLE_
        String authority = role != null && role.startsWith("ROLE_") ? role : "ROLE_USER";

        var user = User.withUsername(username.trim())
                .password(passwordEncoder.encode(password))
                .authorities(List.of(new SimpleGrantedAuthority(authority)))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

        users.createUser(user); // grava em USERS e AUTHORITIES

        // volta para o login com flag de sucesso
        return "redirect:/login?registered";
    }

    public boolean isAuthenticated() {
        return false;
    }
}
