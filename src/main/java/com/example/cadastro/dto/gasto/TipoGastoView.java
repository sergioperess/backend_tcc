package com.example.cadastro.dto.gasto;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;

import java.util.Objects;

public class TipoGastoView {
    private Long id;
    private String nome;

    public TipoGastoView() {
    }

    public TipoGastoView(TipoGasto gasto) {
        this.id = gasto.getId();
        this.nome = gasto.getNome();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoGastoView that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }
}
