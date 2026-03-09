package com.chalange.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

/**
 * DTO de resposta para Resposta.
 */
@Getter
@AllArgsConstructor
public class RespostaResponseDTO {
    private Long id;
    private String mensagem;
    private OffsetDateTime criadoEm;
    private Long autorId;
    private String autorNome;
    private boolean solucao;
}