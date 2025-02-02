package com.example.cadastro.repository;

import com.example.cadastro.entity.TipoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoGastoRepository extends JpaRepository<TipoGasto, Long> {
    @Query(value = "SELECT * FROM tipo_gasto WHERE user_id = ?1", nativeQuery = true)
    public List<TipoGasto> findAllByUserId(Long userId);
}
