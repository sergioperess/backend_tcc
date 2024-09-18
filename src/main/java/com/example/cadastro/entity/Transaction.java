package com.example.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transacao")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private float valor;
    @Column(nullable = false)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "gasto_id")
    private TipoGasto type = null;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user = null;

    public Transaction() {
    }

    public Transaction(float valor, TipoGasto type, String description, User user) {
        this.valor = valor;
        this.type = type;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public TipoGasto getType() {
        return type;
    }

    public void setType(TipoGasto type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getValor(), that.getValor()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValor(), getDate(), getType(), getDescription(), getUser());
    }
}
