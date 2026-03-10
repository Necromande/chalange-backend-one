package com.chalange.api.web.dto;

import com.chalange.api.domain.models.Resposta;
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

    public RespostaResponseDTO(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.criadoEm = resposta.getCriadoEm();
        this.autorId = resposta.getAutor().getId();
        this.autorNome = resposta.getAutor().getNome();
        this.solucao = resposta.isSolucao();
    }
}