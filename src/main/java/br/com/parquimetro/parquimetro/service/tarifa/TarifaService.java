package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.TarifaDTO;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.persiste.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class TarifaService {

    protected TarifaRepository repository;

    public TarifaService(TarifaRepository repository) {
        this.repository = repository;
    }

    protected TarifaDTO toTarifaDTO(Tarifa tarifa) {
        return new TarifaDTO(
                tarifa.getId(),
                tarifa.getPrecoIntervalo(),
                tarifa.getInvervalo(),
                tarifa.getParquimetros()

        );
    }

    protected Tarifa toEntity(RequestTarifaDTO dto) {
        Tarifa tarifa = new Tarifa();

        tarifa.setPrecoIntervalo(dto.precoIntervalo());
        tarifa.setInvervalo(dto.intervalo());

        return tarifa;
    }
}
