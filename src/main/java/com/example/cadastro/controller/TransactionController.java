package com.example.cadastro.controller;

import com.example.cadastro.dto.UserUpdateDTO;
import com.example.cadastro.dto.UserView;
import com.example.cadastro.dto.transaction.TransactionDTO;
import com.example.cadastro.dto.transaction.TransactionUpdateDTO;
import com.example.cadastro.dto.transaction.TransactionView;
import com.example.cadastro.entity.Transaction;
import com.example.cadastro.entity.User;
import com.example.cadastro.service.imple.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/transaction")
@Tag(name = "Transaction Controller", description = "Endpoints to manipulate a Transaction")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Método utilizado para criar uma transação
     *
     * @param transactionDTO Objeto de transação para troca de dados
     * @return ResponseEntity Created - 201 e a transação criada
     */
    @PostMapping
    @Operation(summary = "Cadastrar Transação",
            description = "Método utilizado para cadastrar uma nova Transação")
    public ResponseEntity<TransactionView> save(@RequestBody @Valid TransactionDTO transactionDTO) {

        Transaction transaction = transactionDTO.toEntity();
        transaction.setDate(LocalDateTime.now());
        var entity = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionView(entity));
    }

    /**
     * Método utilizado para encontrar uma transação por id
     *
     * @param id Id da transação
     * @return ResponseEntity OK - Status code 200 e a transação
     */
    @GetMapping("{id}")
    @Operation(summary = "Encontrar uma transação",
            description = "Encontrar uma transação pelo id")
    public ResponseEntity<TransactionView> findById(@PathVariable("id") Long id) {

        var entity = transactionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new TransactionView(entity));

    }

    /**
     * Método utilizado para apagar uma transação por id (Não há retorno)
     *
     * @param id id do usuário
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma transação",
            description = "Deletar uma transação pelo id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.transactionService.delete(id);
    }

    /**
     * Método utilizado para editar uma transação
     *
     * @param id                   - Id do transação
     * @param transactionUpdateDTO - RequestBody
     * @return ResponseEntity OK - Status code 200 e a transação atualizado
     */
    @PatchMapping
    @Operation(summary = "Editar uma transação", description = "Função para editar uma transação")
    public ResponseEntity<TransactionView> updateUser(
            @RequestParam(value = "transactionId") Long id,
            @RequestBody @Valid TransactionUpdateDTO transactionUpdateDTO) {

        Transaction updatedTransaction = transactionUpdateDTO.toEntity(this.transactionService.findById(id));
        Transaction savedTransaction = this.transactionService.save(updatedTransaction);

        return ResponseEntity.status(HttpStatus.OK).body(new TransactionView(savedTransaction));
    }

    /**
     * Método utilizado para listar todas as Transações
     *
     * @return Lista de transações
     */
    @GetMapping
    @Operation(summary = "Listar todas as transações",
            description = "Função para listar todas as transações")
    public ResponseEntity<List<TransactionView>> findAll() {
        List<Transaction> transactions = this.transactionService.findAll();

        List<TransactionView> entity = transactions.stream()
                .map(transaction -> new ModelMapper().map(transaction, TransactionView.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }

    /**
     * Método utilizado para listar as transações de um usário pelo seu id
     *
     * @param userId id do usuário
     * @return Lista de transações
     */
    @GetMapping("list/{id}")
    @Operation(summary = "Listar Gastos de uma pessoa",
            description = "Lista os Gastos de uma pessoa por meio de seu Id")
    public ResponseEntity<List<TransactionView>> findAllByUserId(@PathVariable("id") Long userId) {
        List<TransactionView> transactionViews = this.transactionService.findAllByUserId(userId).stream()
                .map(TransactionView::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(transactionViews);
    }

}
