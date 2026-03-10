package com.chalange.api.domain.repository;

import com.chalange.api.domain.models.Topico;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String cursoNome);

    boolean existsByTituloAndMensagem(@NotBlank(message = "Título é obrigatório") String titulo, @NotBlank(message = "Mensagem é obrigatória") String mensagem);
}

