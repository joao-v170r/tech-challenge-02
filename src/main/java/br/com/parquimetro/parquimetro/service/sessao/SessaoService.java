package br.com.parquimetro.parquimetro.service.sessao;

import br.com.parquimetro.parquimetro.controller.exception.ControllerNotFoundException;
import br.com.parquimetro.parquimetro.dto.sessao.CriarSessaoDTO;
import br.com.parquimetro.parquimetro.dto.sessao.SessaoDTO;
import br.com.parquimetro.parquimetro.model.Pagamento;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.model.context.StatusSessao;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import br.com.parquimetro.parquimetro.repository.SessaoRepository;
import br.com.parquimetro.parquimetro.service.pagamento.PagamentoService;
import br.com.parquimetro.parquimetro.service.parquimetro.ParquimetroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
                new ControllerNotFoundException("Sessão não encontrada"));;
        return toDTO(sessao);
    }

    public Page<SessaoDTO> findAllByParquimetro(Long parquimetroId, Pageable pageable) {
        Page<Sessao> sessoes = sessaoRepository.findByParquimetroId(parquimetroId, pageable);
        return sessoes.map(this::toDTO);
    }

    public SessaoDTO save(CriarSessaoDTO criarSessaoDTO) {
        Parquimetro parquimetro = parquimetroRepository.findById(criarSessaoDTO.parquimetroId())
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível encontrar o parquímetro com o id: " + criarSessaoDTO.parquimetroId()));
        Sessao sessao = new Sessao(
                criarSessaoDTO.placaCarro(),
                parquimetro
        );
        return toDTO(sessaoRepository.save(sessao));
    }

    public SessaoDTO update(Long id) {
        try {
            Sessao sessao = sessaoRepository.getReferenceById(id);
            sessao.setDtSaida(LocalDateTime.now());
            sessao.setStatusSessao(StatusSessao.FINALIZADA);
            Duration tempoDecorrido = Duration.between(sessao.getDtEntrada(), sessao.getDtSaida());
            Duration tolerancia = Duration.between(LocalTime.MIN, sessao.getParquimetro().getTolerancia());
            if (tempoDecorrido.toMillis() > tolerancia.toMillis()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setDtPagamento(LocalDateTime.now());
                pagamento.setSessao(sessao);
                BigDecimal custoSessao = sessao.getCustoSessao();
                pagamento.setValorPagamento(custoSessao);
                sessao.setPagamento(pagamento);
            }
            sessao = sessaoRepository.save(sessao);
            return toDTO(sessao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException(e.getMessage());
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