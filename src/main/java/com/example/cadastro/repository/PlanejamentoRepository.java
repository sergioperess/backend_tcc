package com.example.cadastro.repository;

import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.entity.TipoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {
    @Query(value = "SELECT * FROM planejamento WHERE gasto_id = ?1", nativeQuery = true)
    public List<Planejamento> findAllByGastoId(Long gastoId);

}
