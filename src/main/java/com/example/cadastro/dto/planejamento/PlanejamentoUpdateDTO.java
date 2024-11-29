package com.example.cadastro.dto.planejamento;


import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.entity.TipoGasto;
import jakarta.validation.constraints.NotNull;

public class PlanejamentoUpdateDTO {
    @NotNull(message = "Invalid input")
    private float valorPlanejado;
    private int mes;
    private int ano;
    private Long tipoId;

    public PlanejamentoUpdateDTO() {
    }

    public PlanejamentoUpdateDTO(Planejamento planejamento) {
        this.valorPlanejado = planejamento.getValorPlanejado();
        this.mes = planejamento.getMes();
        this.ano = planejamento.getAno();
        this.tipoId = planejamento.getTipoGasto().getId();
    }


    public Planejamento toEntity(Planejamento planejamento){
        TipoGasto tipoGasto = new TipoGasto();
        tipoGasto.setId(this.tipoId);

        planejamento.setValorPlanejado(this.valorPlanejado);
        planejamento.setMes(this.mes);
        planejamento.setAno(this.ano);
        planejamento.setTipoGasto(tipoGasto);

        return planejamento;
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
}
