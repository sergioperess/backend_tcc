package com.example.cadastro.service;

import com.example.cadastro.entity.User;

/**
 *  Interface para definir os serviços
 */
public interface UserService {
    public User save(User user);

    public User findById(Long id);

    public void delete(Long id);
}
