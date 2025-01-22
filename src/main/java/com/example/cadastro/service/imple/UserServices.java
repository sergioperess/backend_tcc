package com.example.cadastro.service.imple;

import com.example.cadastro.entity.User;
import com.example.cadastro.exceptions.BusinessException;
import com.example.cadastro.repository.UserRepository;
import com.example.cadastro.service.IUserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements IUserService {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método para criar um usuário
     *
     * @param user Usuário
     * @return Usuário
     */
    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Método para procurar um usuário por Id
     *
     * @param id Id
     * @return Usuário
     */
    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id: " + id + " não encontrado"));
    }

    /**
     * Método para deletar um usuário pelo id
     *
     * @param userId Id do usuário
     */
    @Override
    public void delete(Long userId) {
        User user = this.findById(userId);
        this.userRepository.delete(user);
    }

    /**
     * Método para retornar todos os usuários por ordem alfabética
     *
     * @return Lista de usuários
     */
    @Override
    public List<User> findAll() {
        // Utilizado para ordenação
        Sort sort = Sort.by("firstName").ascending()
                .and(Sort.by("lastName").ascending());
        return this.userRepository.findAll(sort);
    }

}
