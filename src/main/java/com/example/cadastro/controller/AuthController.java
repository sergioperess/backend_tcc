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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity login(HttpServletResponse response, @RequestBody LoginDTO body){

        // Utilizado para procurar o usuário no banco de dados
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new BusinessException("User not found"));
        // Verifica se a senha está correta
        if(passwordEncoder.matches(body.senha(), user.getSenha())){
            // Se as senhas forem iguais, gera um token para o usuário
            String token = this.tokenService.generateToken(user);

            // Criando o cookie com o token JWT
            Cookie authCookie = new Cookie("authToken", token);
            authCookie.setMaxAge(7 * 24 * 60 * 60); // Definindo o tempo de expiração para 7 dias
            authCookie.setHttpOnly(true); // Tornando o cookie acessível apenas via HTTP (não acessível por JavaScript)
            authCookie.setSecure(false); // Definindo o cookie para não ser transmitido apenas via HTTPS (recomendado em produção)
            authCookie.setPath("/"); // Tornando o cookie disponível em toda a aplicação

            // Adicionando o cookie à resposta HTTP
            response.addCookie(authCookie);

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

    @GetMapping("/setCookie")
    public String setCookie(String token, HttpServletResponse response) {
        // Criando o cookie com o token JWT
        Cookie authCookie = new Cookie("authToken", token);
        authCookie.setMaxAge(7 * 24 * 60 * 60); // Definindo o tempo de expiração para 7 dias
        authCookie.setHttpOnly(true); // Tornando o cookie acessível apenas via HTTP (não acessível por JavaScript)
        authCookie.setSecure(false); // Definindo o cookie para não ser transmitido apenas via HTTPS (recomendado em produção)
        authCookie.setPath("/"); // Tornando o cookie disponível em toda a aplicação

        // Adicionando o cookie à resposta HTTP
        response.addCookie(authCookie);

        return "Login bem-sucedido! O token foi armazenado no cookie.";
    }

    @GetMapping("/get-token")
    public String getToken(HttpServletRequest request) {
        // Obtém todos os cookies da requisição
        Cookie[] cookies = request.getCookies();

        // Verifica se há cookies e procura pelo cookie de autenticação
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    // Retorna o valor do token armazenado no cookie
                    return "Token encontrado: " + cookie.getValue();
                }
            }
        }
        return "Token não encontrado!";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Criando um cookie com o mesmo nome e valor nulo, para remover
        Cookie authCookie = new Cookie("authToken", null);
        authCookie.setMaxAge(0); // Define o tempo de vida como 0 para remover o cookie
        authCookie.setPath("/"); // Certifique-se de que o caminho seja o mesmo do cookie original

        // Adicionando o cookie à resposta para removê-lo do navegador
        response.addCookie(authCookie);

        return "Logout realizado! O token foi removido.";
    }

}


