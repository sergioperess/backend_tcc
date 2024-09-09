package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.Transaction;
import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    @NotNull(message = "Invalid input")
    private BigDecimal valor;
    @NotEmpty(message = "Invalid input")
    private String type;
    @NotEmpty(message = "Invalid input")
    private String description;
    @NotNull
    private Long userId;

    public TransactionDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Transaction toEntity() {
        User user = new User();
        user.setId(this.userId);
        return new Transaction(
                this.valor,
                this.type,
                this.description,
                user
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;
        return Objects.equals(getValor(), that.getValor()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValor(), getType(), getDescription(), getUserId());
    }
}
