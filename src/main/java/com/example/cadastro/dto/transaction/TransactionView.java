package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TransactionView {
    private Long id;
    private BigDecimal transaction;
    private LocalDateTime date;
    private String type;
    private String description;

    public TransactionView() {
    }

    public TransactionView(Transaction gasto) {
        this.id = gasto.getId();
        this.transaction = gasto.getTransaction();
        this.date = gasto.getDate();
        this.type = gasto.getType();
        this.description = gasto.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof TransactionView that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTransaction(), getDate(), getType(), getDescription());
    }
}
