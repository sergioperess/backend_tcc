package com.example.cadastro.controller;

import com.example.cadastro.dto.gasto.GastoUpdateDTO;
import com.example.cadastro.dto.gasto.TipoGastoDTO;
import com.example.cadastro.dto.gasto.TipoGastoView;
import com.example.cadastro.dto.transaction.TransactionUpdateDTO;
import com.example.cadastro.dto.transaction.TransactionView;
import com.example.cadastro.entity.TipoGasto;
import com.example.cadastro.entity.Transaction;
import com.example.cadastro.service.imple.ITipoGastoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/gastos")
@Tag(name = "TipoGasto Controller", description = "Endpoints para manipular um tipo de gasto")
public class TipoGastoController {

    private final ITipoGastoServices service;

    public TipoGastoController(ITipoGastoServices service) {
        this.service = service;
    }


    @PostMapping
    @Operation(summary = "Cadastrar Tipo de Gasto",
            description = "Método utilizado para cadastrar um novo Tipo de Gasto")
    public ResponseEntity<TipoGastoView> save(@RequestBody @Valid TipoGastoDTO tipoGastoDTO) {

        TipoGasto tipoGasto = tipoGastoDTO.toEntity();
        var entity = service.save(tipoGasto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TipoGastoView(entity));
    }

    @GetMapping("list/{id}")
    @Operation(summary = "Listar o tipo de Gastos de uma pessoa",
            description = "Lista o tipo de Gastos de uma pessoa por meio de seu Id")
    public ResponseEntity<List<TipoGastoView>> findAllByUserId(@PathVariable("id") Long userId) {
        List<TipoGastoView> tipoGastoViews = this.service.findAllByUserId(userId).stream()
                .map(TipoGastoView::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(tipoGastoViews);
    }

    @GetMapping("/{gastoId}/{id}")
    @Operation(summary = "Listar o Id do tipo de Gastos de uma pessoa",
            description = "Lista o Id do tipo de Gastos de uma pessoa por meio de seu Id para poder criar um novo tipo de gasto")
    public ResponseEntity<TipoGastoView> tipoGastoId(
            @PathVariable("gastoId") String tipoGasto,
            @PathVariable("id") Long userId)
    {
        TipoGasto entity = this.service.tipoGastoId(tipoGasto, userId);

        return ResponseEntity.status(HttpStatus.OK).body(new TipoGastoView(entity));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um gasto",
            description = "Deletar um gasto id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }

    @PatchMapping
    @Operation(summary = "Editar um Gasto", description = "Função para editar um gasto")
    public ResponseEntity<TipoGastoView> updateGasto(
            @RequestParam(value = "gastoId") Long id,
            @RequestBody @Valid GastoUpdateDTO gastoUpdateDTO) {

        TipoGasto updatedGasto = gastoUpdateDTO.toEntity(this.service.findById(id));
        TipoGasto savedTransaction = this.service.save(updatedGasto);

        return ResponseEntity.status(HttpStatus.OK).body(new TipoGastoView(savedTransaction));
    }



}
