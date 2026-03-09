package com.chalange.api.infra.service;

import com.chalange.api.domain.exception.InvalidOperationException;
import com.chalange.api.domain.exception.ResourceNotFoundException;
import com.chalange.api.domain.models.Curso;
import com.chalange.api.domain.models.Resposta;
import com.chalange.api.domain.models.Topico;
import com.chalange.api.domain.models.Usuario;
import com.chalange.api.domain.repository.CursoRepository;
import com.chalange.api.domain.repository.RespostaRepository;
import com.chalange.api.domain.repository.TopicoRepository;
import com.chalange.api.domain.repository.UsuarioRepository;
import com.chalange.api.mapper.TopicoMapper;
import com.chalange.api.mapper.RespostaMapper;
import com.chalange.api.web.dto.RespostaRequestDTO;
import com.chalange.api.web.dto.RespostaResponseDTO;
import com.chalange.api.web.dto.TopicoRequestDTO;
import com.chalange.api.web.dto.TopicoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final RespostaRepository respostaRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository,
                         RespostaRepository respostaRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.respostaRepository = respostaRepository;
    }

    @Transactional
    public TopicoResponseDTO criar(TopicoRequestDTO dto) {
        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado."));

        Curso curso = null;
        if (dto.getCursoId() != null) {
            curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado."));
        }

        Topico topico = Topico.builder()
                .titulo(dto.getTitulo())
                .mensagem(dto.getMensagem())
                .criadoEm(OffsetDateTime.now())
                .autor(autor)
                .curso(curso)
                .build();

        Topico salvo = topicoRepository.saveAndFlush(topico);
        return TopicoMapper.toDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<TopicoResponseDTO> listar(String cursoNome) {
        List<Topico> topicos;
        if (cursoNome == null || cursoNome.isBlank()) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCursoNome(cursoNome);
        }
        return topicos.stream().map(TopicoMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TopicoResponseDTO buscarPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));
        return TopicoMapper.toDTO(topico);
    }

    @Transactional
    public TopicoResponseDTO atualizar(Long id, TopicoRequestDTO dto) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));

        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado."));
            topico.setCurso(curso);
        } else {
            topico.setCurso(null);
        }

        Topico atualizado = topicoRepository.saveAndFlush(topico);
        return TopicoMapper.toDTO(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tópico não encontrado.");
        }
        topicoRepository.deleteById(id);
    }

    @Transactional
    public RespostaResponseDTO adicionarResposta(Long topicoId, RespostaRequestDTO dto) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));

        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado."));

        Resposta resposta = Resposta.builder()
                .mensagem(dto.getMensagem())
                .criadoEm(OffsetDateTime.now())
                .autor(autor)
                .topico(topico)
                .solucao(false)
                .build();

        Resposta salvo = respostaRepository.saveAndFlush(resposta);

        // opcional: adicionar à lista de respostas do tópico (se necessário)
        topico.getRespostas().add(salvo);
        topicoRepository.saveAndFlush(topico);

        return RespostaMapper.toDTO(salvo);
    }

    @Transactional
    public void marcarSolucao(Long topicoId, Long respostaId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado."));

        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new ResourceNotFoundException("Resposta não encontrada."));

        if (!resposta.getTopico().getId().equals(topico.getId())) {
            throw new InvalidOperationException("Resposta não pertence ao tópico informado.");
        }

        // Se já existe solução, desmarca a anterior (regra de negócio; ajuste conforme necessidade)
        topico.getRespostas().stream()
                .filter(Resposta::isSolucao)
                .findFirst()
                .ifPresent(r -> {
                    r.setSolucao(false);
                    respostaRepository.save(r);
                });

        resposta.setSolucao(true);
        respostaRepository.saveAndFlush(resposta);
    }
}