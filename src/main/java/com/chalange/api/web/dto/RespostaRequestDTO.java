package com.chalange.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para criação de resposta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespostaRequestDTO {

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotNull(message = "AutorId é obrigatório")
    private Long autorId;
}