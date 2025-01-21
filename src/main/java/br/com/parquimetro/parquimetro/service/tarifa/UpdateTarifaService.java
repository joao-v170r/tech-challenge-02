package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.tarifa.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.tarifa.TarifaDTO;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UpdateTarifaService extends TarifaService {

    @Autowired
    public UpdateTarifaService(TarifaRepository repository) {
        super(repository);
    }

    public TarifaDTO update(Long id, RequestTarifaDTO dto) {
        Tarifa tarifa = repository.getReferenceById(id);
        tarifa.setInvervalo(LocalTime.parse(dto.intervalo()));
        tarifa.setPrecoIntervalo(dto.precoIntervalo());
        return toTarifaDTO(repository.save(tarifa));
    }
}
