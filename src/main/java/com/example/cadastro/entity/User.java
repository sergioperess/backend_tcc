package com.example.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
            mappedBy = "user")
    @JsonManagedReference
    private List<Transaction> transactions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
            mappedBy = "user")
    @JsonManagedReference
    private List<TipoGasto> tipoGastos;


    /**
     *  Construtor vazio
     */
    public User() {
    }

    public User(String firstName, String lastName, String cpf, String senha, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<TipoGasto> getTipoGastos() {
        return tipoGastos;
    }

    public void setTipoGastos(List<TipoGasto> tipoGastos) {
        this.tipoGastos = tipoGastos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getCpf(), user.getCpf()) && Objects.equals(getSenha(), user.getSenha()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getTransactions(), user.getTransactions()) && Objects.equals(getTipoGastos(), user.getTipoGastos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getCpf(), getSenha(), getEmail(), getTransactions(), getTipoGastos());
    }
}
