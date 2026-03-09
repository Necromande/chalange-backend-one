package com.chalange.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO de resposta para Topico.
 * Contém informações do autor e do curso (id/nome) e lista de respostas (opcional).
 */
@Getter
@AllArgsConstructor
public class TopicoResponseDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private OffsetDateTime criadoEm;
    private Long autorId;
    private String autorNome;
    private Long cursoId;
    private String cursoNome;
    private List<RespostaResponseDTO> respostas; // pode ser null ou empty
}