package com.example.cadastro.dto;

import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;

public class UserUpdateDTO {
    @NotEmpty(message = "Invalid input")
    private String firstName;
    @NotEmpty(message = "Invalid input")
    private String lastName;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public User toEntity(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
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

}
