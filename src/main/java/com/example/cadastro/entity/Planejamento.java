package com.example.cadastro.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "planejamento")
public class Planejamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private float valorPlanejado;
    @Column(nullable = false)
    private int mes;
    @Column(nullable = false)
    private int ano;
    @ManyToOne
    @JoinColumn(name = "gasto_id")
    private TipoGasto tipoGasto = null;

    public Planejamento() {
    }

    public Planejamento(float valorPlanejado, int mes, int ano, TipoGasto tipoGasto) {
        this.valorPlanejado = valorPlanejado;
        this.mes = mes;
        this.ano = ano;
        this.tipoGasto = tipoGasto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planejamento that)) return false;
        return Float.compare(getValorPlanejado(), that.getValorPlanejado()) == 0 && getAno() == that.getAno() && Objects.equals(getId(), that.getId()) && Objects.equals(getMes(), that.getMes()) && Objects.equals(getTipoGasto(), that.getTipoGasto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValorPlanejado(), getMes(), getAno(), getTipoGasto());
    }
}
