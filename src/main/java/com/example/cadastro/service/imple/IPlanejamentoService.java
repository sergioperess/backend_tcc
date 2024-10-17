package com.example.cadastro.service.imple;

import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.repository.PlanejamentoRepository;
import com.example.cadastro.repository.TipoGastoRepository;
import com.example.cadastro.service.PlanejamentoService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPlanejamentoService implements PlanejamentoService {
    private final PlanejamentoRepository planejamentoRepository;
    private final ITipoGastoServices services;

    public IPlanejamentoService(PlanejamentoRepository planejamentoRepository, ITipoGastoServices services) {
        this.planejamentoRepository = planejamentoRepository;
        this.services = services;
    }

    @Override
    public List<Planejamento> findAllByGastoId(Long gastoId) {
        services.findById(gastoId);

        return this.planejamentoRepository.findAllByGastoId(gastoId);
    }

    @Override
    public Planejamento salvarPlanejamento(Planejamento planejamento) {
        planejamento.setTipoGasto(services.findById(planejamento.getTipoGasto().getId()));

        return planejamentoRepository.save(planejamento);
    }
}
