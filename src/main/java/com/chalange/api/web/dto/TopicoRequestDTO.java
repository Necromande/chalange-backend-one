package com.chalange.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para criação/atualização de tópico.
 * - cursoId pode ser null (tópico sem curso).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotNull(message = "AutorId é obrigatório")
    private Long autorId;

    // opcional: pode ser null se o tópico não estiver associado a um curso
    private Long cursoId;
}