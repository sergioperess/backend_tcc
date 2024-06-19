package com.example.cadastro.dto;

import com.example.cadastro.entity.User;

import java.util.Objects;

public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String modeloCelular;

    public UserView(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getSenha();
        this.email = user.getEmail();
        this.modeloCelular = user.getModeloCelular();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof UserView userView)) return false;
        return Objects.equals(getId(), userView.getId()) && Objects.equals(getFirstName(), userView.getFirstName()) && Objects.equals(getLastName(), userView.getLastName()) && Objects.equals(getPassword(), userView.getPassword()) && Objects.equals(getEmail(), userView.getEmail()) && Objects.equals(getModeloCelular(), userView.getModeloCelular());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPassword(), getEmail(), getModeloCelular());
    }
}

