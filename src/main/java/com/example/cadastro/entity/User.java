package com.example.cadastro.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String modeloCelular;

    /**
     *  Construtor vazio
     */
    public User() {
    }

    public User(String firstName, String lastName, String cpf, String senha, String email, String modeloCelular) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.modeloCelular = modeloCelular;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getCpf(), user.getCpf()) && Objects.equals(getSenha(), user.getSenha()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getModeloCelular(), user.getModeloCelular());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getCpf(), getSenha(), getEmail(), getModeloCelular());
    }
}
