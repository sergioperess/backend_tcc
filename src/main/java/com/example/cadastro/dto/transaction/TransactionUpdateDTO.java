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
    private int mes;
    private int ano;

    public TransactionUpdateDTO() {
    }

    public TransactionUpdateDTO(Transaction transactions) {
        this.valor = transactions.getValor();
        this.typeId = transactions.getType().getId();
        this.description = transactions.getDescription();
        this.mes = transactions.getMes();
        this.ano = transactions.getAno();
    }

    public Transaction toEntity(Transaction transactions){
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.typeId);

        transactions.setValor(this.valor);
        transactions.setDescription(this.description);
        transactions.setType(tipoGasto);
        transactions.setMes(this.mes);
        transactions.setAno(this.ano);

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

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
