package com.example.cadastro.repository;

import com.example.cadastro.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM TRANSACAO WHERE USER_ID = ?1", nativeQuery = true)
    public List<Transaction> findAllByUserId(Long userId);
}
