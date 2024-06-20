package com.example.cadastro.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private BigDecimal transaction;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String description;

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTransaction(), that.getTransaction()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTransaction(), getDate(), getType(), getDescription());
    }
}
