package com.example.cadastro.dto.gasto;

import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class TipoGastoDTO {
    @NotEmpty(message = "Invalid input")
    private String nome;
    @NotNull
    private Long userId;

    public TipoGastoDTO() {
    }

    public TipoGastoDTO(TipoGasto gasto) {
        this.nome = gasto.getNome();
    }

    public TipoGasto toEntity(){
        User user = new User();
        user.setId(this.userId);
        return new TipoGasto(
                this.nome,
                user
        );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoGastoDTO that)) return false;
        return Objects.equals(getNome(), that.getNome()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getUserId());
    }
}
