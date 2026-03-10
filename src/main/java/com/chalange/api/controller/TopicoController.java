package com.chalange.api.controller;

import com.chalange.api.infra.service.TopicoService;
import com.chalange.api.web.dto.TopicoRequestDTO;
import com.chalange.api.web.dto.TopicoResponseDTO;
import com.chalange.api.web.dto.TopicoUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault; // Para o Controller
import org.springframework.data.domain.Sort;          // Para o Controller
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> criar(@RequestBody @Valid TopicoRequestDTO dto) {
        var criado = service.salvar(dto);
        return ResponseEntity.created(URI.create("/topicos/" + criado.getId())).body(criado);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listar(
            @PageableDefault(size = 5, sort = "criadoEm", direction = Sort.Direction.DESC) Pageable paginacao) {

        var pagina = service.listar(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateDTO dados) {
        var topico = service.atualizar(id, dados);
        return ResponseEntity.ok(topico);
    }
}