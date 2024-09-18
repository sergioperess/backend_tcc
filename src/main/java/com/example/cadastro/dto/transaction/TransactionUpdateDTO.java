package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionUpdateDTO {
    @NotNull(message = "Invalid input")
    private float valor;
    @NotNull
    private Long typeId;
    @NotEmpty(message = "Invalid input")
    private String description;
    private LocalDateTime date;

    public TransactionUpdateDTO() {
    }

    public TransactionUpdateDTO(Transaction transactions) {
        this.valor = transactions.getValor();
        this.typeId = transactions.getType().getId();
        this.description = transactions.getDescription();
        this.date = transactions.getDate();
    }

    public Transaction toEntity(Transaction transactions){
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.typeId);

        transactions.setValor(this.valor);
        transactions.setDescription(this.description);
        transactions.setType(tipoGasto);
        transactions.setDate(LocalDateTime.now());

        return transactions;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
