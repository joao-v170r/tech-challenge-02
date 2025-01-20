package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.sessao.CriarSessaoDTO;
import br.com.parquimetro.parquimetro.dto.sessao.SessaoDTO;
import br.com.parquimetro.parquimetro.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
@Tag(name = "Sessão", description = "Endpoints de uma sessão de um veículo.")  // Add a description for this controller
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtém dados de uma sessão.",
            description = "Obtém todos os dados de uma sessão através do id de uma sessão."
    )
    public ResponseEntity<SessaoDTO> findById(@PathVariable Long id) {
        SessaoDTO sessaoDTO = sessaoService.findById(id);
        return ResponseEntity.ok(sessaoDTO);
    }

    @GetMapping("/parquimetro/{id}")
    @Operation(
            summary = "Obtém todas as sessões de um parquimetro.",
            description = "Obtém todas as sessões de um parquimetro através do id do parquímetro."
    )
    public ResponseEntity<Page<SessaoDTO>> findAllByParquimetro(@PathVariable Long id,
            @PageableDefault(page = 0, size = 10, sort = "nome") Pageable pageable
    ) {
        Page<SessaoDTO> sessaoDTOS = sessaoService.findAllByParquimetro(id, pageable);
        return ResponseEntity.ok(sessaoDTOS);
    }

    @PostMapping
    @Operation(
            summary = "Cria uma sessão.",
            description = "Veículo entrou no estacionamento, sessão em andamento."
    )
    public ResponseEntity<SessaoDTO> save(@Valid @RequestBody CriarSessaoDTO criarSessaoDTO) {
        SessaoDTO sessaoDTOSaved = sessaoService.save(criarSessaoDTO);
        return new ResponseEntity<>(sessaoDTOSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Conclui a sessão.",
            description = "Veículo saiu do estacionamento. O status da sessão é alterado para concluído. " +
                    "É avaliado se o veículo ficou no estacionamento além da tolerância, " +
                    "e caso tenha ficado, é calculado o valor a ser cobrado " +
                    "e gerado a ordem de pagamento."
    )
    public ResponseEntity<SessaoDTO> update(@PathVariable Long id) {
        SessaoDTO sessaoDTOUpdated = sessaoService.update(id);
        return ResponseEntity.ok(sessaoDTOUpdated);
    }
}