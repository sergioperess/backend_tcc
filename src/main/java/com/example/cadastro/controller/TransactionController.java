package com.example.cadastro.controller;

import com.example.cadastro.dto.UserView;
import com.example.cadastro.dto.transaction.TransactionDTO;
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

    @PostMapping
    @Operation(summary = "Cadastrar Transação",
            description = "Método utilizado para cadastrar uma nova Transação")
    public ResponseEntity<TransactionView> save(@RequestBody @Valid TransactionDTO transactionDTO) {

        transactionDTO.setDate(LocalDateTime.now());
        Transaction transaction = new ModelMapper().map(transactionDTO, Transaction.class);
        var entity = transactionService.save(transaction);

        TransactionView transactionView = new ModelMapper().map(entity, TransactionView.class);


        return ResponseEntity.status(HttpStatus.CREATED).body(transactionView);
    }

    /**
     *  Métdodo utilizado para listar todas as Transações
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
}
