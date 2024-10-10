package com.example.cadastro.service.imple;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;
import com.example.cadastro.entity.User;
import com.example.cadastro.exceptions.BusinessException;
import com.example.cadastro.repository.TipoGastoRepository;
import com.example.cadastro.service.TipoGastoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITipoGastoServices implements TipoGastoService {

    private final TipoGastoRepository repository;
    private final IUserServices userServices;

    public ITipoGastoServices(TipoGastoRepository repository, IUserServices userServices) {
        this.repository = repository;
        this.userServices = userServices;
    }

    @Override
    public TipoGasto save(TipoGasto tipoGasto) {
        tipoGasto.setUser(userServices.findById(tipoGasto.getUser().getId()));
        return this.repository.save(tipoGasto);
    }

    @Override
    public TipoGasto findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new BusinessException("Id: " + id + " não encontrado"));
    }

    @Override
    public List<TipoGasto> findAllByUserId(Long userId) {
        userServices.findById(userId);

        return this.repository.findAllByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        TipoGasto gasto = this.findById(id);
        this.repository.delete(gasto);
    }

    @Override
    public List<TipoGasto> findAll() {
        return this.repository.findAll();
    }


    public TipoGasto tipoGastoId(String tipoGasto, Long userId) {

        String aux = tipoGasto.substring(0, 1).toUpperCase() + tipoGasto.substring(1).toLowerCase();
        List<TipoGasto> tipoGastos = findAllByUserId(userId);

        for (TipoGasto gasto : tipoGastos) {
            if (aux.equals(gasto.getNome())) {
                return gasto;
            }
        }

        // Levantar exceção se não encontrar o tipo de gasto
        throw new BusinessException("Tipo de gasto não encontrado para o nome: " + tipoGasto);
    }

}
