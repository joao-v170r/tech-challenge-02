package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.tarifa.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.tarifa.TarifaDTO;
import br.com.parquimetro.parquimetro.service.tarifa.DeleteTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.FindTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.SaveTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.UpdateTarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
@Tag(name = "Tarifa", description = "Endpoints de Tarifa.")
public class TarifaController {

    @Autowired
    private SaveTarifaService saveTarifa;
    @Autowired
    private FindTarifaService findTarifa;
    @Autowired
    private UpdateTarifaService updateTarifa;
    @Autowired
    private DeleteTarifaService deleteTarifa;

    @GetMapping
    @Operation(
            summary = "Lista todas as tarifas.",
            description = "Lista todas as tarifas."
    )
    public ResponseEntity<List<TarifaDTO>> findAll() {
        return ResponseEntity.ok(findTarifa.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtém dados de uma tarifa.",
            description = "Obtém dados de uma tarifa através do id"
    )
    public ResponseEntity<TarifaDTO> find(@PathVariable Long id) {
        return ResponseEntity.ok(findTarifa.find(id));
    }

    @GetMapping("/{id}/parquimetros")
    @Operation(
            summary = "Lista todos os parquimetros da tarifa.",
            description = "Lista todos os parquimetros que pussem uma tarifa atraves do id"
    )
    public ResponseEntity<List<ParquimetroDTO>> findParquimetrosOfTarifa(@PathVariable Long id) {
        return ResponseEntity.ok(findTarifa.findParquimetrosOfTarifa(id));
    }

    @PostMapping
    @Operation(
            summary = "Cria uma tarifa.",
            description = "Cria uma tarifa"
    )
    public ResponseEntity<TarifaDTO> save(@Valid @RequestBody RequestTarifaDTO req) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(saveTarifa.save(req));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deleta uma tarifa.",
            description = "Deleta uma tarifa"
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteTarifa.delete(id);
        return ResponseEntity.noContent().build();
    }
}
