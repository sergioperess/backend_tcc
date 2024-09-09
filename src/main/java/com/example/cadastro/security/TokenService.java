package com.example.cadastro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.cadastro.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Recupera essa chave privada do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // Método para a criação do token
    public String generateToken(User user){
        try{
            // Criação do algoritmo a partir de uma chave privada para criptografia
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Criação do token
            String token = JWT.create()
                    .withIssuer("cadastro")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException e){
            throw new RuntimeException("Error");
        }
    }

    // Método utilizado para a validação do token
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // decodificação do token para pegar o username
            return JWT.require(algorithm)
                    .withIssuer("cadastro")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e) {
            return null;
        }
    }

    // Método privado para gerar um tempo para a expiração do token
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
