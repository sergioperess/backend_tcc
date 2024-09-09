package com.example.cadastro.security;

import com.example.cadastro.entity.User;
import com.example.cadastro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // User na visão do spring security
        // Retorna o username, senha e arraylist de roles
        return new org.springframework.security.core
                .userdetails.User(user.getEmail(), user.getSenha(), new ArrayList<>());
    }
}