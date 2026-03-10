package com.chalange.api.controller;

import com.chalange.api.infra.service.CursoService;
import com.chalange.api.web.dto.CursoRequestDTO;
import com.chalange.api.web.dto.CursoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criar(@RequestBody @Valid CursoRequestDTO dto) {
        var criado = service.salvar(dto);
        return ResponseEntity.created(URI.create("/cursos/" + criado.id())).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}