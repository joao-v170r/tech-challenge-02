package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.parquimetro.RequestLinkTarifaParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.parquimetro.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.service.parquimetro.FindParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.LinkTarifaParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.SaveParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.UpdateParquimetroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parquimetro")
@Tag(name = "Parquimetro", description = "Endpoints de parquimetros.")
public class ParquimetroController {

    @Autowired
    FindParquimetroService findParquimetro;
    @Autowired
    LinkTarifaParquimetroService linkParquimetro;
    @Autowired
    SaveParquimetroService saveParquimetro;
    @Autowired
    UpdateParquimetroService updateParquimetro;

    @GetMapping
    @Operation(
            summary = "Lista todos os parquimetros.",
            description = "Lista todos os parquimetros."
    )
    public ResponseEntity<List<ParquimetroDTO>> findAll() {
        return ResponseEntity.ok(findParquimetro.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtém dados de um parquimetro.",
            description = "Obtém todos os dados de um parquimetro através do id do parquimetro."
    )
    public ResponseEntity<ParquimetroDTO> find(@PathVariable Long id) {
        return ResponseEntity.ok(findParquimetro.find(id));
    }

    @PostMapping
    @Operation(
            summary = "Cria um parquimetro.",
            description = "Criar um parquimetro."
    )
    public ResponseEntity<ParquimetroDTO> save(@Valid @RequestBody RequestParquimetroDTO req) {
        return  ResponseEntity.status(HttpStatusCode.valueOf(201)).body(saveParquimetro.save(req));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualiza um parquimetro.",
            description = "Atualiza um parquimetro."
    )
    public ResponseEntity<ParquimetroDTO> update(@PathVariable Long id,@Valid @RequestBody RequestParquimetroDTO req) {
        return ResponseEntity.ok(updateParquimetro.update(id, req));
    }

    @PostMapping("/link-tarifa")
    @Operation(
            summary = "Vincula tarifa",
            description = "Vincula uma tarifa a um parquimetro"

    )
    public ResponseEntity<ParquimetroDTO> link(@Valid @RequestBody RequestLinkTarifaParquimetroDTO req) {
        return ResponseEntity.ok(linkParquimetro.link(req.parquimetroId(), req.tarifaId()));
    }

    @DeleteMapping("/unlink-tarifa")
    @Operation(
            summary = "Desvincula tarifa",
            description = "Desvincula uma tarifa de um parquimetro"

    )
    public ResponseEntity<ParquimetroDTO> unlink(@Valid @RequestBody RequestLinkTarifaParquimetroDTO req) {
        return ResponseEntity.ok(linkParquimetro.unlink(req.parquimetroId(), req.tarifaId()));
    }
}
