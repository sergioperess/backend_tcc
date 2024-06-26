package com.example.cadastro.service.imple;

import com.example.cadastro.entity.Transaction;
import com.example.cadastro.entity.User;
import com.example.cadastro.repository.TransactionRepository;
import com.example.cadastro.service.TransactionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITransactionService implements TransactionService {
    private TransactionRepository transactionRepository;

    public ITransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Método utilizado para cadastrar uma nova transação
     *
     * @param transaction Transação
     * @return Transação
     */
    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }
}
