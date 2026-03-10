package com.chalange.api.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(
        @NotBlank String nome,
        @NotBlank String categoria
) {}