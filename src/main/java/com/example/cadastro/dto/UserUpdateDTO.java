package com.example.cadastro.dto;

import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;

public class UserUpdateDTO {
    @NotEmpty(message = "Invalid input")
    private String firstName;
    @NotEmpty(message = "Invalid input")
    private String lastName;
    @NotEmpty(message = "Invalid input")
    private String senhaAtual;
    @NotEmpty(message = "Invalid input")
    private String senha;
    @NotEmpty(message = "Invalid input")
    private String email;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.senhaAtual = user.getSenha();
        this.senha = user.getSenha();
        this.email = user.getEmail();
    }

    public User toEntity(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setSenha(this.senha);
        user.setEmail(this.email);
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }
}
