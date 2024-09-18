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

    public Transaction toEntity() {
        User user = new User();
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.typeId);
        user.setId(this.userId);
        return new Transaction(
                this.valor,
                tipoGasto,
                this.description,
                user
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;
        return Float.compare(getValor(), that.getValor()) == 0 && Objects.equals(getTypeId(), that.getTypeId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValor(), getTypeId(), getDescription(), getUserId());
    }
}
