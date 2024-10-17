package com.example.cadastro.dto.planejamento;

import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.entity.TipoGasto;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


public class PlanejamentoDTO {
    @NotNull(message = "Invalid input")
    private float valorPlanejado;
    @NotNull(message = "Invalid input")
    private int mes;
    @NotNull(message = "Invalid input")
    private int ano;
    @NotNull
    private Long tipoId;

    public PlanejamentoDTO() {
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

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Planejamento toEntity(){
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.tipoId);
        return new Planejamento(
                this.valorPlanejado,
                this.mes,
                this.ano,
                tipoGasto
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanejamentoDTO that)) return false;
        return Float.compare(getValorPlanejado(), that.getValorPlanejado()) == 0 && getMes() == that.getMes() && getAno() == that.getAno() && Objects.equals(getTipoId(), that.getTipoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValorPlanejado(), getMes(), getAno(), getTipoId());
    }
}
