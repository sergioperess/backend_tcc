package com.example.cadastro.service;

import com.example.cadastro.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    public Transaction save(Transaction transaction);

    public Transaction findById(Long id);

    public void delete(Long id);

    public List<Transaction> findAll();

    public List<Transaction> findAllByUserId(Long userId);
}
