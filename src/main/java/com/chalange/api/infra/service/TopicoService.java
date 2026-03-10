package com.chalange.api.infra.service;

import com.chalange.api.domain.exception.BusinessException;
import com.chalange.api.domain.exception.ResourceNotFoundException;
import com.chalange.api.domain.repository.CursoRepository;
import com.chalange.api.domain.repository.TopicoRepository;
import com.chalange.api.domain.repository.UsuarioRepository;
import com.chalange.api.mapper.TopicoMapper;
import com.chalange.api.web.dto.TopicoRequestDTO;
import com.chalange.api.web.dto.TopicoResponseDTO;
import com.chalange.api.web.dto.TopicoUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository repository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public TopicoResponseDTO salvar(TopicoRequestDTO dto) {
        if (repository.existsByTituloAndMensagem(dto.getTitulo(), dto.getMensagem())) {
            throw new BusinessException("Tópico já cadastrado com este título e mensagem.");
        }

        var autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        var topico = TopicoMapper.toEntity(dto);
        topico.setAutor(autor);
        topico.setCriadoEm(OffsetDateTime.now());

        if (dto.getCursoId() != null) {
            var curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
            topico.setCurso(curso);
        }

        return TopicoMapper.toDTO(repository.save(topico));
    }

    @Transactional(readOnly = true)
    public Page<TopicoResponseDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(TopicoResponseDTO::new);
    }
    @Transactional(readOnly = true)
    public TopicoResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(TopicoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado"));
    }
    @Transactional
    public TopicoResponseDTO atualizar(Long id, TopicoUpdateDTO dados) {
        // 1. Tenta buscar o tópico pelo ID
        var topico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));

        // 2. Atualiza os campos (o Hibernate detecta a mudança automaticamente aqui)
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        // 3. Converte a entidade atualizada para DTO e retorna
        return new TopicoResponseDTO(topico);
    }
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tópico não encontrado");
        }
        repository.deleteById(id);
    }


}