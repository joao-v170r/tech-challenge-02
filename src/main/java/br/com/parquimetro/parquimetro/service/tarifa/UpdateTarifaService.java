package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.TarifaDTO;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.persiste.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateTarifaService extends TarifaService {

    @Autowired
    public UpdateTarifaService(TarifaRepository repository) {
        super(repository);
    }

    public TarifaDTO update(Long id, RequestTarifaDTO dto) {
        Tarifa tarifa = repository.getReferenceById(id);
        tarifa.setInvervalo(dto.intervalo());
        tarifa.setPrecoIntervalo(dto.precoIntervalo());
        return toTarifaDTO(repository.save(tarifa));
    }
}
