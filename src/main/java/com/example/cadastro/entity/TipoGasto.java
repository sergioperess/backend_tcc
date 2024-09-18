package com.example.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gasto")
public class TipoGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
            mappedBy = "type")
    @JsonManagedReference
    private List<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user = null;

    public TipoGasto() {
    }

    public TipoGasto(String nome, User user) {
        this.nome = nome;
        this.user = user;
    }

    public TipoGasto(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoGasto tipoGasto)) return false;
        return Objects.equals(getId(), tipoGasto.getId()) && Objects.equals(getNome(), tipoGasto.getNome()) && Objects.equals(getTransactions(), tipoGasto.getTransactions()) && Objects.equals(getUser(), tipoGasto.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTransactions(), getUser());
    }
}
