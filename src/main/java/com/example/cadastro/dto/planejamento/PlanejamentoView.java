package com.example.cadastro.dto.planejamento;


import com.example.cadastro.entity.Planejamento;

import java.util.Objects;

public class PlanejamentoView {
    private Long id;
    private float valorPlanejado;
    private String type;
    private int mes;
    private int ano;

    public PlanejamentoView() {
    }

    public PlanejamentoView(Planejamento planejamento) {
        this.id = planejamento.getId();
        this.valorPlanejado = planejamento.getValorPlanejado();
        this.type = planejamento.getTipoGasto().getNome();
        this.mes = planejamento.getMes();
        this.ano = planejamento.getAno();
    }

    public float getValorPlanejado() {
        return valorPlanejado;
    }

    public void setValorPlanejado(float valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanejamentoView that)) return false;
        return Float.compare(getValorPlanejado(), that.getValorPlanejado()) == 0 && getMes() == that.getMes() && getAno() == that.getAno() && Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValorPlanejado(), getType(), getMes(), getAno());
    }
}
