package com.example.cadastro.dto.transaction;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TransactionView {
    private Long id;
    private float valor;
    private String type;
    private String description;
    private int dia;
    private int mes;
    private int ano;

    public TransactionView() {
    }

    public TransactionView(Transaction gasto) {
        this.id = gasto.getId();
        this.valor = gasto.getValor();
        this.type = gasto.getType().getNome();
        this.description = gasto.getDescription();
        this.dia = gasto.getDia();
        this.mes = gasto.getMes();
        this.ano = gasto.getAno();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionView that)) return false;
        return Float.compare(getValor(), that.getValor()) == 0 && getDia() == that.getDia() && getMes() == that.getMes() && getAno() == that.getAno() && Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValor(), getType(), getDescription(), getDia(), getMes(), getAno());
    }
}
