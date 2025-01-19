package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.service.tarifa.DeleteTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.FindTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.SaveTarifaService;
import br.com.parquimetro.parquimetro.service.tarifa.UpdateTarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
