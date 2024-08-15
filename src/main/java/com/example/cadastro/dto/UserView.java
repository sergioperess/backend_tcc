package com.example.cadastro.dto;

import com.example.cadastro.entity.User;

import java.util.Objects;

public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
    private String senha;
    private String email;

    public UserView(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.senha = user.getSenha();
        this.email = user.getEmail();
    }

    public UserView() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserView userView)) return false;
        return Objects.equals(getId(), userView.getId()) && Objects.equals(getFirstName(), userView.getFirstName()) && Objects.equals(getLastName(), userView.getLastName()) && Objects.equals(getSenha(), userView.getSenha()) && Objects.equals(getEmail(), userView.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getSenha(), getEmail());
    }
}

