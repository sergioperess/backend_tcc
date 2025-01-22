package com.example.cadastro.service;

import com.example.cadastro.entity.TipoGasto;

import java.util.List;

public interface ITipoGastoService {
    public TipoGasto save(TipoGasto tipoGasto);

    public TipoGasto findById(Long id);

    public List<TipoGasto> findAllByUserId(Long userId);

    public void delete(Long id);

    public List<TipoGasto> findAll();
}
