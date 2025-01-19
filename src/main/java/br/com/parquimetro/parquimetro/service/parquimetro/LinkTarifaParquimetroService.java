package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.erro.ServiceParquimetroErro;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.persiste.ParquimetroRepository;
import br.com.parquimetro.parquimetro.persiste.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LinkTarifaParquimetroService extends ParquimetroService {

    TarifaRepository tarifaRepository;

    @Autowired
    public LinkTarifaParquimetroService(ParquimetroRepository repository,  TarifaRepository tarifaRepository) {
        super(repository);
        this.tarifaRepository = tarifaRepository;
    }

    public ParquimetroDTO link(Long parquimetroId, Long tarifaId) {
        Parquimetro parquimetro = repository.findById(parquimetroId)
                .orElseThrow(() -> new ServiceParquimetroErro("id parquimetro não encontrado"));
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new ServiceParquimetroErro("id tarifa não encontrado"));

        parquimetro.addTarifa(tarifa);

        return toParquimetroDTO(repository.save(parquimetro));
    }

    public ParquimetroDTO unlink(Long parquimetroId, Long tarifaId) {
        Parquimetro parquimetro = repository.findById(parquimetroId)
                .orElseThrow(() -> new ServiceParquimetroErro("id parquimetro não encontrado"));
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new ServiceParquimetroErro("id tarifa não encontrado"));

        parquimetro.removeTarifa(tarifa);

        return toParquimetroDTO(parquimetro);
    }
}
