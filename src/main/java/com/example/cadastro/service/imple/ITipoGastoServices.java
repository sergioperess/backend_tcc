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


    public TipoGasto tipoGastoId(String tipoGasto, Long userId) {

        String aux = tipoGasto.substring(0, 1).toUpperCase() + tipoGasto.substring(1).toLowerCase();

        List<TipoGasto> tipoGastos = new ArrayList<>(findAllByUserId(userId));


        for (int i = 0; i < tipoGastos.size(); i++) {
            if (aux.equals(tipoGastos.get(i).getNome())) {
                return tipoGastos.get(i);
            }
        }

        User user = new User();
        user.setId(userId);

        TipoGasto tipo = new TipoGasto(tipoGasto, user);

        return this.repository.save(tipo);
    }
}
