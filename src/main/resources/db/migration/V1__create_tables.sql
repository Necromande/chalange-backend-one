CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         CONSTRAINT uq_usuario_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE usuario_perfis (
                                usuario_id BIGINT NOT NULL,
                                perfis_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfis_id),
                                CONSTRAINT fk_usuario_perfis_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
                                CONSTRAINT fk_usuario_perfis_perfil FOREIGN KEY (perfis_id) REFERENCES perfil(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       categoria VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensagem TEXT NOT NULL,
                        data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50),
                        autor_id BIGINT,
                        curso_id BIGINT,
                        CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuario(id) ON DELETE SET NULL,
                        CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE resposta (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          mensagem TEXT NOT NULL,
                          data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          solucao BOOLEAN,
                          autor_id BIGINT,
                          topico_id BIGINT,
                          CONSTRAINT fk_resposta_autor FOREIGN KEY (autor_id) REFERENCES usuario(id) ON DELETE SET NULL,
                          CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topico(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;