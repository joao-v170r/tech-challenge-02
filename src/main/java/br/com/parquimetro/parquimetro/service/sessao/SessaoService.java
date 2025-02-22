package br.com.parquimetro.parquimetro.service.sessao;

import br.com.parquimetro.parquimetro.erro.not_found.SessaoNotFoundException;
import br.com.parquimetro.parquimetro.dto.sessao.RequestSessaoDTO;
import br.com.parquimetro.parquimetro.dto.sessao.SessaoDTO;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.model.context.StatusSessao;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import br.com.parquimetro.parquimetro.repository.SessaoRepository;
import br.com.parquimetro.parquimetro.util.CustoSessao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ParquimetroRepository parquimetroRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository, ParquimetroRepository parquimetroRepository) {
        this.sessaoRepository = sessaoRepository;
        this.parquimetroRepository = parquimetroRepository;
    }

    public SessaoDTO findById(Long id) {
        Sessao sessao = sessaoRepository.findById(id).orElseThrow(() ->
                new SessaoNotFoundException("Sessão não encontrada"));;
        return toDTO(sessao);
    }

    public Page<SessaoDTO> findAllByParquimetro(Long parquimetroId, Pageable pageable) {
        Page<Sessao> sessoes = sessaoRepository.findByParquimetroId(parquimetroId, pageable);
        return sessoes.map(this::toDTO);
    }

    public SessaoDTO create(RequestSessaoDTO requestSessaoDTO) {
        Parquimetro parquimetro = parquimetroRepository.findById(requestSessaoDTO.parquimetroId())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar o parquímetro com o id: " + requestSessaoDTO.parquimetroId()));
        Sessao sessao = new Sessao(
                requestSessaoDTO.placaCarro(),
                parquimetro
        );
        return toDTO(sessaoRepository.save(sessao));
    }

    public SessaoDTO finish(Long id) {
        try {
            Sessao sessao = sessaoRepository.getReferenceById(id);
            boolean isPeriodoTolerancia = CustoSessao.getTempoDecorridoSessao(sessao)
                                                .compareTo(CustoSessao.converterParaDuration(sessao.getParquimetro().getTolerancia())) <= 0;

            // Não permite saída se não foi realizado o pagamento E ja estiver ultrapassado o periodo de tolerancia.
            if (!isPeriodoTolerancia && sessao.getPagamento() == null) {
                return toDTO(sessao);
            }

            // Não permite saída se o cliente ficou até a próxima tarifa.
            BigDecimal custoSessao = CustoSessao.getCustoSessao(sessao);
            if (!isPeriodoTolerancia && sessao.getPagamento().getValorPagamento().compareTo(custoSessao) != 0) {
                return toDTO(sessao);
            }

            // Concluir sessão.
            sessao.setDtSaida(LocalDateTime.now());
            sessao.setStatusSessao(StatusSessao.FINALIZADA);
            sessao = sessaoRepository.save(sessao);
            return toDTO(sessao);
        } catch (EntityNotFoundException e) {
            throw new SessaoNotFoundException(e.getMessage());
        }
    }

    private SessaoDTO toDTO(Sessao sessao) {
        return new SessaoDTO(
                sessao.getId(),
                sessao.getPlacaCarro(),
                sessao.getDtEntrada(),
                sessao.getDtSaida(),
                sessao.getPagamento(),// == null ? null : PagamentoService.toPagamentoDTO(sessao.getPagamento()),
                sessao.getParquimetro(),// == null ? null : ParquimetroService.toParquimetroDTO(sessao.getParquimetro()),
                sessao.getStatusSessao()
        );
    }
}