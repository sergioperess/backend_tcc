package com.example.cadastro.service.imple;

import com.example.cadastro.entity.Transaction;
import com.example.cadastro.exceptions.BusinessException;
import com.example.cadastro.repository.TransactionRepository;
import com.example.cadastro.service.ITipoGastoService;
import com.example.cadastro.service.ITransactionService;
import com.example.cadastro.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    private TransactionRepository transactionRepository;
    private IUserService userService;

    private ITipoGastoService tipoGastoService;

    public TransactionService(
            TransactionRepository transactionRepository,
            IUserService userService,
            ITipoGastoService tipoGastoService)
    {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.tipoGastoService = tipoGastoService;
    }

    /**
     * Método utilizado para cadastrar uma nova transação
     *
     * @param transaction Transação
     * @return Transação
     */
    @Override
    public Transaction save(Transaction transaction) {
        transaction.setType(tipoGastoService.findById(transaction.getType().getId()));
        transaction.setUser(userService.findById(transaction.getUser().getId()));
        return this.transactionRepository.save(transaction);
    }

    /**
     * Método utilizado para procurar uma transação por id
     *
     * @param id Id
     * @return Transação
     */
    @Override
    public Transaction findById(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id: " + id + " não encontrado"));
    }

    /**
     * Método para deletar uma transação pelo id
     *
     * @param id Id da transação
     */
    @Override
    public void delete(Long id) {
        Transaction transaction = this.findById(id);
        this.transactionRepository.delete(transaction);
    }

    /**
     * Método para retornar todos as transações
     *
     * @return Lista de transações
     */
    @Override
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    /**
     * Método para retornar uma lista de transações pelo id do usuário
     *
     * @param userId Id do usuário
     * @return Lista de transações
     */
    @Override
    public List<Transaction> findAllByUserId(Long userId) {
        userService.findById(userId);

        return this.transactionRepository.findAllByUserId(userId);
    }
}
