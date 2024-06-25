package com.example.cadastro.service;

import com.example.cadastro.entity.Transaction;

import java.util.List;

public interface TransactionService {
    public Transaction save(Transaction transaction);

    public List<Transaction> findAll();
}
