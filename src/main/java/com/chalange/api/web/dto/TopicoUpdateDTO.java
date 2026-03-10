package com.chalange.api.web.dto;


import jakarta.validation.constraints.NotBlank;

public record TopicoUpdateDTO(
        @NotBlank(message = "O título não pode estar em branco")
        String titulo,

        @NotBlank(message = "A mensagem não pode estar em branca")
        String mensagem
) {}