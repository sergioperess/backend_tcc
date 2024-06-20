package com.example.cadastro.dto.transaction;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDTO {
    @NotNull(message = "Invalid input")
    private BigDecimal transaction;
    private LocalDateTime date;
    @NotEmpty(message = "Invalid input")
    private String type;
    @NotEmpty(message = "Invalid input")
    private String description;

    public TransactionDTO() {
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO that)) return false;
        return Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransaction(), getDate(), getType(), getDescription());
    }
}
