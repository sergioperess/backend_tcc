package com.example.cadastro.controller;

import com.example.cadastro.dto.planejamento.PlanejamentoDTO;
import com.example.cadastro.dto.planejamento.PlanejamentoUpdateDTO;
import com.example.cadastro.dto.planejamento.PlanejamentoView;
import com.example.cadastro.entity.Planejamento;
import com.example.cadastro.service.imple.IPlanejamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/planejamento")
@Tag(name = "Planejamento Controller", description = "Endpoints para manipular um planejamento para um tipo de gasto")
public class PlanejamentoController {

    private final IPlanejamentoService service;

    public PlanejamentoController(IPlanejamentoService service) {
        this.service = service;
    }

    @GetMapping("list/{id}")
    @Operation(summary = "Listar o planejamento de um tipo de Gastos de uma pessoa",
            description = "Lista o planejamento de um tipo de Gastos de uma pessoa por meio de seu Id")
    public ResponseEntity<List<PlanejamentoView>> findAllByGastoId(
            @PathVariable("id") Long gastoId) {
       List<PlanejamentoView> planejamentoViews = this.service.findAllByGastoId(gastoId)
               .stream().map(PlanejamentoView::new).toList();;

        return ResponseEntity.status(HttpStatus.OK).body(planejamentoViews);
    }

    @PostMapping
    @Operation(summary = "Cadastrar Planejamento",
            description = "Método utilizado para cadastrar um novo Planejamento")
    public ResponseEntity<PlanejamentoView> salvar(@RequestBody PlanejamentoDTO planejamento) {
        Planejamento salvo = planejamento.toEntity();
        var entity = service.salvarPlanejamento(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PlanejamentoView(entity));
    }

    /**
     * Método utilizado para apagar um planejamento por id (Não há retorno)
     *
     * @param id Id do planejamento
     */
    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um planejamento",
            description = "Deletar um planejamento pelo id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.service.delete(id);
    }

    /**
     * Método utilizado para editar um planejamento
     *
     * @param id                   - Id do planejamento
     * @param planejamentoUpdateDTO - RequestBody
     * @return ResponseEntity OK - Status code 200 e o planejamento atualizado
     */
    @PatchMapping
    @Operation(summary = "Editar um planejamento", description = "Função para editar um planejamento")
    public ResponseEntity<PlanejamentoView> updatePlanejamento(
            @RequestParam(value = "planejamentoId") Long id,
            @RequestBody @Valid PlanejamentoUpdateDTO planejamentoUpdateDTO) {

        Planejamento updatedPlanejamento = planejamentoUpdateDTO.toEntity(this.service.findById(id));
        Planejamento savedPlanejamento = this.service.salvarPlanejamento(updatedPlanejamento);

        return ResponseEntity.status(HttpStatus.OK).body(new PlanejamentoView(savedPlanejamento));
    }

    /**
     * Método utilizado para encontrar um planejamento por id
     *
     * @param id Id do planejamento
     * @return ResponseEntity OK - Status code 200 e o planejamento
     */
    @GetMapping("{id}")
    @Operation(summary = "Encontrar um planejamento",
            description = "Encontrar um planejamento pelo id")
    public ResponseEntity<PlanejamentoView> findById(@PathVariable("id") Long id) {

        var entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new PlanejamentoView(entity));

    }
}
