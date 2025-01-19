package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.service.parquimetro.FindParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.SaveParquimetroService;
import br.com.parquimetro.parquimetro.service.parquimetro.UpdateParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parquimetro")
public class ParquimetroController {

    @Autowired
    FindParquimetroService findParquimetro;
    @Autowired
    SaveParquimetroService saveParquimetro;
    @Autowired
    UpdateParquimetroService updateParquimetro;

    @GetMapping
    public ResponseEntity<List<ParquimetroDTO>> findAll() {
        return ResponseEntity.ok(findParquimetro.findAll());
    }

    @PostMapping
    public ResponseEntity<ParquimetroDTO> save(@RequestBody RequestParquimetroDTO req) {
        return ResponseEntity.ok(saveParquimetro.save(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParquimetroDTO> update(@PathVariable Long id, @RequestBody RequestParquimetroDTO req) {
        return ResponseEntity.ok(updateParquimetro.update(id, req));
    }
}
