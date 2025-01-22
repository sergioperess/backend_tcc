package com.example.cadastro.controller;

import com.example.cadastro.dto.UserDTO;
import com.example.cadastro.dto.UserUpdateDTO;
import com.example.cadastro.dto.UserView;
import com.example.cadastro.entity.User;
import com.example.cadastro.service.imple.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Endpoints to manipulate a User")
public class UserController {
    private final UserServices userServices;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Método utilizado para criar um usuário
     *
     * @param userDTO Objeto de usuário para troca de dados
     * @return ResponseEntity Created - 201  e o usuário criado
     */
    @PostMapping
    @Operation(summary = "Criar Usuário",
            description = "Método utilizado para criar um Usuário")
    public ResponseEntity<UserView> save(@RequestBody @Valid UserDTO userDTO) {

        User user = userDTO.toEntity();
        var entity = userServices.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserView(entity));
    }

    /**
     * Método utilizado para encontrar um usuário por id
     *
     * @param id do usuário
     * @return ResponseEntity OK - Status code 200 e o usuário
     */
    @GetMapping("{id}")
    @Operation(summary = "Encontrar um usuário",
            description = "Encontrar um usuário pelo id")
    public ResponseEntity<UserView> findById(@PathVariable("id") Long id) {

        var entity = userServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserView(entity));

    }

    /**
     * Método utilizado para apagar um usuário por id (Não há retorno)
     *
     * @param userId Id do usuário
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um usuário",
            description = "Deletar um usuário pelo id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long userId) {
        this.userServices.delete(userId);
    }

    /**
     * Método utilizado para editar um usuário
     *
     * @param id            - Id do usuário
     * @param userUpdateDTO - RequestBody
     * @return ResponseEntity OK - Status code 200 e o usuário atualizado
     */
    @PatchMapping
    @Operation(summary = "Editar um usuário", description = "Função para editar um usuário")
    public ResponseEntity<UserView> updateUser(
            @RequestParam(value = "userId") Long id,
            @RequestBody @Valid UserUpdateDTO userUpdateDTO) {

        User user = this.userServices.findById(id);

        // Verifica se a senha atual está correta
        if (!passwordEncoder.matches(userUpdateDTO.getSenhaAtual(), user.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Senha atual inválida
        }

        User updatedUser = userUpdateDTO.toEntity(this.userServices.findById(id));
        updatedUser.setSenha(passwordEncoder.encode(userUpdateDTO.getSenha()));
        User savedUser = this.userServices.save(updatedUser);

        return ResponseEntity.status(HttpStatus.OK).body(new UserView(savedUser));
    }


    /**
     * Método utilizado para listar todos os usuários em ordem alfabética
     * @return Lista de usuários
     */
    @GetMapping
    @Operation(summary = "Listar todos os usuários",
            description = "Função para listar todos os usuários em ordem alfabética")
    public ResponseEntity<List<UserView>> findAll() {
        List<User> users = this.userServices.findAll();

        List<UserView> entity = users.stream()
                .map(user -> new ModelMapper().map(user, UserView.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

}

