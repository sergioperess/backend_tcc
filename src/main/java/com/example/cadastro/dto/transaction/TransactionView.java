package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TransactionView {
    private Long id;
    private BigDecimal valor;
    private LocalDateTime date;
    private String type;
    private String description;

    public TransactionView() {
    }

    public TransactionView(Transaction gasto) {
        this.id = gasto.getId();
        this.valor = gasto.getValor();
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        return Objects.equals(getId(), that.getId()) && Objects.equals(getValor(), that.getValor()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValor(), getDate(), getType(), getDescription());
    }
}
