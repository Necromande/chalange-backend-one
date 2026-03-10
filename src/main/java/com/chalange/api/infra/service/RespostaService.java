package com.chalange.api.infra.service;

import com.chalange.api.domain.exception.ResourceNotFoundException;
import com.chalange.api.domain.repository.RespostaRepository;
import com.chalange.api.domain.repository.TopicoRepository;
import com.chalange.api.domain.repository.UsuarioRepository;
import com.chalange.api.mapper.RespostaMapper;
import com.chalange.api.web.dto.RespostaRequestDTO;
import com.chalange.api.web.dto.RespostaResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class RespostaService {

    private final RespostaRepository repository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public RespostaService(RespostaRepository repository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public RespostaResponseDTO salvar(Long topicoId, RespostaRequestDTO dto) {
        var topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado"));

        var autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

        var resposta = RespostaMapper.toEntity(dto);
        resposta.setTopico(topico);
        resposta.setAutor(autor);
        resposta.setCriadoEm(OffsetDateTime.now());

        return RespostaMapper.toDTO(repository.save(resposta));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resposta não encontrada");
        }
        repository.deleteById(id);
    }
}