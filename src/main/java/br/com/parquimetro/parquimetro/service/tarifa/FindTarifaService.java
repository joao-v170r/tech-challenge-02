package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.tarifa.TarifaDTO;
import br.com.parquimetro.parquimetro.erro.not_found.TarifaNotFoundErro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;
import br.com.parquimetro.parquimetro.service.parquimetro.ParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTarifaService extends TarifaService {

    @Autowired
    public FindTarifaService(TarifaRepository repository) {
        super(repository);
    }

    public List<TarifaDTO> findAll() {
        List<Tarifa> listTarifa = repository.findAll();
        return listTarifa.stream().map(FindTarifaService::toTarifaDTO).toList();
    }

    public TarifaDTO find(Long id) {
        Tarifa tarifa = repository.findById(id).orElseThrow(() -> new TarifaNotFoundErro("id tarifa não existe"));
        return toTarifaDTO(tarifa);
    }

    public List<ParquimetroDTO> findParquimetrosOfTarifa(Long id) {
        Tarifa tarifa = repository.findById(id).orElseThrow(() -> new TarifaNotFoundErro("id tarifa não existe"));
        return tarifa.getParquimetros().stream().map(ParquimetroService::toParquimetroDTO).toList();
    }
}
