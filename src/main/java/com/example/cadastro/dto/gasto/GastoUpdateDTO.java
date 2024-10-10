package com.example.cadastro.dto.gasto;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GastoUpdateDTO {
    @NotEmpty(message = "Invalid input")
    private String nome;

    public GastoUpdateDTO() {
    }

    public GastoUpdateDTO(TipoGasto gasto) {
        this.nome = gasto.getNome();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoGasto toEntity(TipoGasto gasto){
        gasto.setNome(this.nome);

        return gasto;
    }

}
