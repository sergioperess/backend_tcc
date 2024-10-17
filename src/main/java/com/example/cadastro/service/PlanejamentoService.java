package com.example.cadastro.service;

import com.example.cadastro.entity.Planejamento;

import java.util.List;

public interface PlanejamentoService {
    public List<Planejamento> findAllByGastoId(Long gastoId);
    public Planejamento salvarPlanejamento(Planejamento planejamento);
}
