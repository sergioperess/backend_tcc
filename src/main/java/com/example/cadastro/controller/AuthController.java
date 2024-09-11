package com.example.cadastro.controller;

import com.example.cadastro.dto.UserDTO;
import com.example.cadastro.dto.auth.LoginDTO;
import com.example.cadastro.dto.auth.LoginView;
import com.example.cadastro.entity.User;
import com.example.cadastro.exceptions.BusinessException;
import com.example.cadastro.repository.UserRepository;
import com.example.cadastro.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Endpoints de login e cadastro de usuário
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Endpoints para manipular o login e cadastro de usuários")
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login do usuário",
            description = "Método utilizado para fazer o login de um Usuário e gerar um token para autenticação")
    public ResponseEntity login(@RequestBody LoginDTO body){
        // Utilizado para procurar o usuário no banco de dados
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new BusinessException("User not found"));
        // Verifica se a senha está correta
        if(passwordEncoder.matches(body.senha(), user.getSenha())){
            // Se as senhas forem iguais, gera um token para o usuário
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginView(user.getId(),user.getEmail(), token));
        }
        //return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BusinessException("Senha incorreta"));
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar Usuário",
            description = "Método utilizado para registrar um Usuário e gerar um token para autenticação")
    public ResponseEntity register(@RequestBody @Valid UserDTO body) {
        Optional<User> user = this.repository.findByEmail(body.getEmail());
        if(user.isEmpty()){
            User newUser = new User();
            // Salva a senha de forma criptografada no bando de dados
            newUser.setSenha(passwordEncoder.encode(body.getSenha()));
            newUser.setEmail(body.getEmail());
            newUser.setFirstName(body.getFirstName());
            newUser.setLastName(body.getLastName());
            newUser.setCpf(body.getCpf());
            this.repository.save(newUser);

            // Geração do token após o usuário ser criado
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginView(newUser.getId(), newUser.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}
