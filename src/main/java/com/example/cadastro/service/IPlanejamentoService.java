package com.example.cadastro.service;

import com.example.cadastro.entity.Planejamento;

import java.util.List;

public interface IPlanejamentoService {
    public List<Planejamento> findAllByGastoId(Long gastoId);
    public Planejamento salvarPlanejamento(Planejamento planejamento);
    public void delete(Long id);
}
