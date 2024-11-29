package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;
import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    @NotNull(message = "Invalid input")
    private float valor;
    @NotNull
    private Long typeId;
    @NotNull(message = "Invalid input")
    private int dia;
    @NotNull(message = "Invalid input")
    private int mes;
    @NotNull(message = "Invalid input")
    private int ano;
    @NotEmpty(message = "Invalid input")
    private String description;
    @NotNull
    private Long userId;

    public TransactionDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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

    public Transaction toEntity() {
        User user = new User();
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.typeId);
        user.setId(this.userId);
        return new Transaction(
                this.valor,
                tipoGasto,
                this.dia,
                this.mes,
                this.ano,
                this.description,
                user
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;
        return Float.compare(getValor(), that.getValor()) == 0 && getDia() == that.getDia() && getMes() == that.getMes() && getAno() == that.getAno() && Objects.equals(getTypeId(), that.getTypeId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValor(), getTypeId(), getDia(), getMes(), getAno(), getDescription(), getUserId());
    }
}
