package com.chalange.api.mapper;

import com.chalange.api.domain.models.Topico;
import com.chalange.api.web.dto.TopicoRequestDTO;
import com.chalange.api.web.dto.TopicoResponseDTO;
import com.chalange.api.web.dto.RespostaResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TopicoMapper {

    public static Topico toEntity(TopicoRequestDTO dto) {
        if (dto == null) return null;
        return Topico.builder()
                .titulo(dto.getTitulo())
                .mensagem(dto.getMensagem())
                // criadoEm, autor e curso devem ser preenchidos pelo service
                .build();
    }

    public static TopicoResponseDTO toDTO(Topico topico) {
        if (topico == null) return null;

        List<RespostaResponseDTO> respostas = null;
        if (topico.getRespostas() != null) {
            respostas = topico.getRespostas().stream()
                    .map(RespostaMapper::toDTO)
                    .collect(Collectors.toList());
        }

        Long autorId = topico.getAutor() == null ? null : topico.getAutor().getId();
        String autorNome = topico.getAutor() == null ? null : topico.getAutor().getNome();

        Long cursoId = topico.getCurso() == null ? null : topico.getCurso().getId();
        String cursoNome = topico.getCurso() == null ? null : topico.getCurso().getNome();

        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getCriadoEm(),
                autorId,
                autorNome,
                cursoId,
                cursoNome,
                respostas
        );
    }
}