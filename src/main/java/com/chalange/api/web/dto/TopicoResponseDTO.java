package com.chalange.api.web.dto;

import com.chalange.api.domain.models.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO de resposta para Topico.
 * Contém informações do autor e do curso (id/nome) e lista de respostas (opcional).
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor // Importante para serialização do JSON
public class TopicoResponseDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private OffsetDateTime criadoEm;
    private Long autorId;
    private String autorNome;
    private Long cursoId;
    private String cursoNome;
    private List<RespostaResponseDTO> respostas;

    // Adicione este construtor para facilitar o .map() no Service
    public TopicoResponseDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.criadoEm = topico.getCriadoEm();
        this.autorId = topico.getAutor().getId();
        this.autorNome = topico.getAutor().getNome();
        this.cursoId = topico.getCurso().getId();
        this.cursoNome = topico.getCurso().getNome();
        // Converte a lista de entidades Resposta para DTOs
        this.respostas = topico.getRespostas() != null ?
                topico.getRespostas().stream().map(RespostaResponseDTO::new).toList() :
                new ArrayList<>();
    }
}