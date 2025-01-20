package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.TarifaDTO;
import br.com.parquimetro.parquimetro.erro.ServiceTarifaErro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.persiste.TarifaRepository;
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
        return listTarifa.stream().map(this::toTarifaDTO).toList();
    }

    public TarifaDTO find(Long id) {
        Tarifa tarifa = repository.findById(id).orElseThrow(() -> new ServiceTarifaErro("id tarifa não existe"));
        return toTarifaDTO(tarifa);
    }
}
