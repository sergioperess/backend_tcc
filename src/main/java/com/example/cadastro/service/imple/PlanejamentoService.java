package com.example.cadastro.service.imple;

import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.exceptions.BusinessException;
import com.example.cadastro.repository.PlanejamentoRepository;

import com.example.cadastro.service.IPlanejamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanejamentoService implements IPlanejamentoService {
    private final PlanejamentoRepository planejamentoRepository;
    private final TipoGastoServices services;

    public PlanejamentoService(PlanejamentoRepository planejamentoRepository, TipoGastoServices services) {
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

    public Planejamento findById(Long id){
        return this.planejamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Id: " + id + " não encontrado"));
    }

    @Override
    public void delete(Long id) {
        Planejamento planejamento = this.findById(id);
        this.planejamentoRepository.delete(planejamento);
    }
}
