package com.example.cadastro.dto.auth;

// Classe utilizada como DTO
// record é utilizado pois é um tipo de classe que ja possui os getters e setters automáticos
public record LoginDTO(String email, String senha) {
}
