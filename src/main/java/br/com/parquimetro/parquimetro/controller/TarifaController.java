package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.TarifaDTO;
import br.com.parquimetro.parquimetro.service.tarifa.DeleteTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.FindTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.SaveTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.UpdateTarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    private SaveTarifaService saveTarifa;
    @Autowired
    private FindTarifaService findTarifa;
    @Autowired
    private UpdateTarifaService updateTarifa;
    @Autowired
    private DeleteTarifaService deleteTarifa;

    @PostMapping
    public ResponseEntity<TarifaDTO> save(@RequestBody RequestTarifaDTO req) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(saveTarifa.save(req));
    }
}
