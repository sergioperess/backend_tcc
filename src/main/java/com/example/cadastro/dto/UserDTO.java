package com.example.cadastro.dto;

import com.example.cadastro.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;


public class UserDTO {
    @NotEmpty(message = "Invalid input")
    private String firstName;
    @NotEmpty(message = "Invalid input")
    private String lastName;
    @NotEmpty(message = "Invalid input")
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotEmpty(message = "Invalid input")
    private String senha;
    @NotEmpty(message = "Invalid input")
    @Email(message = "É necessário um email válido")
    private String email;
    @NotEmpty(message = "Invalid input")
    private String modeloCelular;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.cpf = user.getCpf();
        this.senha = user.getSenha();
        this.email = user.getEmail();
        this.modeloCelular = user.getModeloCelular();
    }


    public User toEntity() {
        return new User(
                this.firstName,
                this.lastName,
                this.cpf,
                this.senha,
                this.email,
                this.modeloCelular
        );
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getModeloCelular() {
        return modeloCelular;
    }

    public void setModeloCelular(String modeloCelular) {
        this.modeloCelular = modeloCelular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getFirstName(), userDTO.getFirstName()) && Objects.equals(getLastName(), userDTO.getLastName()) && Objects.equals(getCpf(), userDTO.getCpf()) && Objects.equals(getSenha(), userDTO.getSenha()) && Objects.equals(getEmail(), userDTO.getEmail()) && Objects.equals(getModeloCelular(), userDTO.getModeloCelular());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getCpf(), getSenha(), getEmail(), getModeloCelular());
    }
}

