package com.chalange.api.infra.service;

import com.chalange.api.domain.models.Curso;
import com.chalange.api.domain.repository.CursoRepository;
import com.chalange.api.web.dto.CursoRequestDTO;
import com.chalange.api.web.dto.CursoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CursoResponseDTO salvar(CursoRequestDTO dto) {
        var curso = Curso.builder()
                .nome(dto.nome()) // Removido o 'get'
                .categoria(dto.categoria()) // Removido o 'get'
                .build();

        var salvo = repository.save(curso);
        return new CursoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCategoria());
    }

    public List<CursoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(c -> new CursoResponseDTO(c.getId(), c.getNome(), c.getCategoria()))
                .toList();
    }
}