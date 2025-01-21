package br.com.parquimetro.parquimetro.service.pagamento;

import br.com.parquimetro.parquimetro.erro.not_found.PagamentoNotFoundErro;
import br.com.parquimetro.parquimetro.erro.not_found.SessaoNotFoundException;
import br.com.parquimetro.parquimetro.dto.pagamento.RequestPagamentoDTO;
import br.com.parquimetro.parquimetro.dto.pagamento.PagamentoDTO;
import br.com.parquimetro.parquimetro.model.Pagamento;
import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.repository.PagamentoRepository;
import br.com.parquimetro.parquimetro.repository.SessaoRepository;
import br.com.parquimetro.parquimetro.util.CustoSessao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final SessaoRepository sessaoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository, SessaoRepository sessaoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    public PagamentoDTO save(RequestPagamentoDTO requestPagamentoDTO) {
        Sessao sessao = sessaoRepository.findById(requestPagamentoDTO.sessaoId())
                .orElseThrow(() -> new PagamentoNotFoundErro("não foi possível encontrar a sessão com o id: " + requestPagamentoDTO.sessaoId()));
        Pagamento pagamento = new Pagamento(sessao, CustoSessao.getCustoSessao(sessao),
                requestPagamentoDTO.formaPagamento());
        sessao.setPagamento(pagamento);
        sessaoRepository.save(sessao);
        return toPagamentoDTO(sessao.getPagamento());
    }

    public PagamentoDTO update(Long sessaoId) {
        try {
            Sessao sessao = sessaoRepository.getReferenceById(sessaoId);
            sessao.getPagamento().setDtPagamento(LocalDateTime.now());
            sessao.getPagamento().setValorPagamento(CustoSessao.getCustoSessao(sessao));
            pagamentoRepository.save(sessao);
            return toPagamentoDTO(sessao.getPagamento());
        } catch (EntityNotFoundException e) {
            throw new SessaoNotFoundException(e.getMessage());
        }
    }

    public static PagamentoDTO toPagamentoDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getDtPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getFormaPagamento()
        );
    }
}
