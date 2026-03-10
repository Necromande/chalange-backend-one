package com.chalange.api.controller;

import com.chalange.api.infra.service.RespostaService;
import com.chalange.api.web.dto.RespostaRequestDTO;
import com.chalange.api.web.dto.RespostaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("topicos/{topicoId}/respostas")
public class RespostaController {

    private final RespostaService service;

    public RespostaController(RespostaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RespostaResponseDTO> criar(@PathVariable Long topicoId, @RequestBody @Valid RespostaRequestDTO dto) {
        var criado = service.salvar(topicoId, dto);
        return ResponseEntity.created(URI.create("/respostas/" + criado.getId())).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}