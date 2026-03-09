package com.chalange.api.mapper;

import com.chalange.api.domain.models.Resposta;
import com.chalange.api.web.dto.RespostaRequestDTO;
import com.chalange.api.web.dto.RespostaResponseDTO;

public class RespostaMapper {

    public static Resposta toEntity(RespostaRequestDTO dto) {
        if (dto == null) return null;
        return Resposta.builder()
                .mensagem(dto.getMensagem())
                // criadoEm, autor e topico devem ser preenchidos pelo service
                .build();
    }

    public static RespostaResponseDTO toDTO(Resposta resposta) {
        if (resposta == null) return null;

        Long autorId = resposta.getAutor() == null ? null : resposta.getAutor().getId();
        String autorNome = resposta.getAutor() == null ? null : resposta.getAutor().getNome();

        return new RespostaResponseDTO(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getCriadoEm(),
                autorId,
                autorNome,
                resposta.isSolucao()
        );
    }
}