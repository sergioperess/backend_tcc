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
    private BigDecimal transaction;
    @NotEmpty(message = "Invalid input")
    private String type;
    @NotEmpty(message = "Invalid input")
    private String description;
    @NotNull
    private Long userId;

    public TransactionDTO() {
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction;
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
                this.transaction,
                this.type,
                this.description,
                user
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;
        return Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransaction(), getType(), getDescription(), getUserId());
    }
}
