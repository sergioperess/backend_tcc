package com.example.cadastro.security;

import com.example.cadastro.entity.User;
import com.example.cadastro.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// Filtro que sera executado uma vez para cada request
// Utilizado para fazer a autenticação do usuário
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    // Método do filtro
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Token que veio da request
        var token = this.recoverToken(request);
        // Retorna o username
        var login = tokenService.validateToken(token);

        if(login != null){
            // Encontrar usuário no banco
            User user = userRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));
            // Criar coleção de roles para o usuário
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            // Objeto de autenticação contendo o usuário e as roles
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            // Contexto de segurança do spring security ( Salva as informações do usuário autenticado)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }


    // Método utilizado para autorizar as requisições feitas pelo usuário
    // retorna o token que vem da request
    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}