package com.example.cadastro.dto;

import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;

public class UserUpdateDTO {
    @NotEmpty(message = "Invalid input")
    private String firstName;
    @NotEmpty(message = "Invalid input")
    private String lastName;

    @NotEmpty(message = "Invalid input")
    private String modeloCelular;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.modeloCelular = user.getModeloCelular();
    }

    public User toEntity(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setModeloCelular(this.modeloCelular);
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

    public String getModeloCelular() {
        return modeloCelular;
    }

    public void setModeloCelular(String modeloCelular) {
        this.modeloCelular = modeloCelular;
    }
}
