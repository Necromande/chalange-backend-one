package com.chalange.api.mapper;

import com.chalange.api.domain.models.Usuario;
import com.chalange.api.web.dto.UsuarioRequestDTO;
import com.chalange.api.web.dto.UsuarioResponseDTO;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha()) // encode no service
                .build();
    }

    public static UsuarioResponseDTO toDTO(Usuario u) {
        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail());
    }
}