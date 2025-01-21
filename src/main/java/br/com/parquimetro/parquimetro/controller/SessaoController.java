package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.sessao.CriarSessaoDTO;
import br.com.parquimetro.parquimetro.dto.sessao.SessaoDTO;
import br.com.parquimetro.parquimetro.service.sessao.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
@Tag(name = "Sessão", description = "Endpoints de uma sessão de estacionamento de um veículo.")  // Add a description for this controller
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

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
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable
    ) {
        Page<SessaoDTO> sessaoDTOS = sessaoService.findAllByParquimetro(id, pageable);
        return ResponseEntity.ok(sessaoDTOS);
    }

    @PostMapping("/init")
    @Operation(
            summary = "Cria uma sessão.",
            description = "Veículo entrou no estacionamento, sessão em andamento."
    )
    public ResponseEntity<SessaoDTO> save(@Valid @RequestBody CriarSessaoDTO criarSessaoDTO) {
        sessaoService.save(criarSessaoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(sessaoService.save(criarSessaoDTO));
    }

    @PutMapping("/{id}/finish")
    @Operation(
            summary = "Conclui a sessão.",
            description = "Veículo saiu do estacionamento. Avalia se o cliente pagou o " +
                    "estacionamento e se excedeu o tempo após o pagamento."
    )
    public ResponseEntity<SessaoDTO> update(@PathVariable Long id) {
        SessaoDTO sessaoDTOUpdated = sessaoService.update(id);
        return ResponseEntity.ok(sessaoDTOUpdated);
    }
}