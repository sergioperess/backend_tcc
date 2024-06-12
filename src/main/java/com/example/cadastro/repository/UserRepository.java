package com.example.cadastro.repository;

import com.example.cadastro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface UserRepository
 * Esta interface é um repositório para a entidade User.
 * Ela estende JpaRepository para fornecer métodos CRUD e outros métodos
 * de manipulação de dados para a entidade User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // A interface JpaRepository fornece métodos como save, findById, findAll, deleteById, etc.
    // Não há necessidade de definir métodos adicionais aqui a menos que sejam personalizados.
}
