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
    private int mes;
    @Column(nullable = false)
    private int ano;
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

    public Transaction(float valor, TipoGasto type,int mes,int ano, String description, User user) {
        this.valor = valor;
        this.type = type;
        this.mes = mes;
        this.ano = ano;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Float.compare(getValor(), that.getValor()) == 0 && getMes() == that.getMes() && getAno() == that.getAno() && Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValor(), getMes(), getAno(), getType(), getDescription(), getUser());
    }
}
