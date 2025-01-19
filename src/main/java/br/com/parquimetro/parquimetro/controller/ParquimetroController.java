package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestLinkTarifaParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.service.parquimetro.FindParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.LinkTarifaParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.SaveParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.UpdateParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parquimetro")
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
    public ResponseEntity<List<ParquimetroDTO>> findAll() {
        return ResponseEntity.ok(findParquimetro.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParquimetroDTO> find(@PathVariable Long id) {
        return ResponseEntity.ok(findParquimetro.find(id));
    }

    @PostMapping
    public ResponseEntity<ParquimetroDTO> save(@RequestBody RequestParquimetroDTO req) {
        return ResponseEntity.ok(saveParquimetro.save(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParquimetroDTO> update(@PathVariable Long id, @RequestBody RequestParquimetroDTO req) {
        //TO-DO:Ajustar o restorno para ficar adequado ao rest api
        return ResponseEntity.ok(updateParquimetro.update(id, req));
    }

    @PostMapping("/link-tarifa")
    public ResponseEntity<ParquimetroDTO> link(@RequestBody RequestLinkTarifaParquimetroDTO req) {
        return ResponseEntity.ok(linkParquimetro.link(req.parquimetroId(), req.tarifaId()));
    }

    @DeleteMapping("/unlink-tarifa")
    public ResponseEntity<ParquimetroDTO> unlink(@RequestBody RequestLinkTarifaParquimetroDTO req) {
        return ResponseEntity.ok(linkParquimetro.unlink(req.parquimetroId(), req.tarifaId()));
    }
}
