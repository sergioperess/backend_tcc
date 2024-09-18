package com.example.cadastro.service;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;

import java.util.List;

public interface TipoGastoService {
    public TipoGasto save(TipoGasto tipoGasto);

    public TipoGasto findById(Long id);

    public List<TipoGasto> findAllByUserId(Long userId);
}
