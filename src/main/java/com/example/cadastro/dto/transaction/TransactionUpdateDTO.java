package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.Transaction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionUpdateDTO {
    @NotNull(message = "Invalid input")
    private BigDecimal valor;
    @NotEmpty(message = "Invalid input")
    private String type;
    @NotEmpty(message = "Invalid input")
    private String description;
    private LocalDateTime date;

    public TransactionUpdateDTO() {
    }

    public TransactionUpdateDTO(Transaction transactions) {
        this.valor = transactions.getValor();
        this.type = transactions.getType();
        this.description = transactions.getDescription();
        this.date = transactions.getDate();
    }

    public Transaction toEntity(Transaction transactions){
        transactions.setValor(this.valor);
        transactions.setType(this.type);
        transactions.setDescription(this.description);
        transactions.setDate(LocalDateTime.now());
        return transactions;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
