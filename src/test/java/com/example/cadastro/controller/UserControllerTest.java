package com.example.cadastro.controller;

import com.example.cadastro.dto.UserDTO;
import com.example.cadastro.dto.UserUpdateDTO;
import com.example.cadastro.dto.UserView;
import com.example.cadastro.entity.User;
import com.example.cadastro.service.imple.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserServices userServices;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserUpdateDTO userUpdateDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(); // Inicializando um usuário fictício
        userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setSenhaAtual("senhaAntiga");
        userUpdateDTO.setSenha("novaSenha");
        userUpdateDTO.setFirstName("John");
        userUpdateDTO.setLastName("Doe");
        userUpdateDTO.setEmail("john.doe@example.com");
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        User user = new User();
        when(userServices.save(any(User.class))).thenReturn(user);

        ResponseEntity<UserView> response = userController.save(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userServices, times(1)).save(any(User.class));
    }

    @Test
    public void testFindByUserId() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        User user = new User();
        when(userServices.save(any(User.class))).thenReturn(user);
        when(userServices.findById(userId)).thenReturn(user);

        ResponseEntity<UserView> response = userController.findById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userServices, times(1)).findById(userId);
    }

//    @Test
//    public void testUpdateTask() {
//        Long userId = 1L;
//        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
//        User user = new User();
//        when(userServices.findById(userId)).thenReturn(user);
//        when(userServices.save(any(User.class))).thenReturn(user);
//
//        ResponseEntity<UserView> response = userController.updateUser(userId, userUpdateDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(userServices, times(1)).findById(userId);
//        verify(userServices, times(1)).save(any(User.class));
//    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        userController.delete(userId);

        verify(userServices, times(1)).delete(userId);
    }
}
