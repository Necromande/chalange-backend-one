# 🚀 Fórum Hub API - Challenge Alura

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

O **Fórum Hub** é uma API REST robusta para gerenciamento de tópicos de discussão, simulando o funcionamento interno de um fórum. O projeto foca em boas práticas de desenvolvimento, como separação de responsabilidades (Service/Controller), validação de dados e performance em consultas.

## 🛠️ Tecnologias e Ferramentas

- **Linguagem:** Java 25
- **Framework:** Spring Boot 4.1.0-m2
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** MySQL 9.6
- **Gerenciamento de Dependências:** Maven
- **Validação:** Bean Validation (Hibernate Validator)

---

## 📌 Funcionalidades do Projeto

### 1. Gestão de Tópicos (CRUD)
- **Cadastro:** Criação de novos tópicos vinculados a um autor e curso.
- **Listagem Paginada:** Consulta otimizada de tópicos, evitando sobrecarga de memória.
- **Atualização:** Edição segura de título e mensagem de tópicos existentes.
- **Exclusão:** Remoção física do registro com limpeza automática de dependências (Cascade).

### 2. Paginação e Ordenação
A API utiliza o objeto `Pageable` do Spring para permitir que o cliente escolha a quantidade de registros e a ordem de exibição.
- **Padrão:** 5 itens por página, ordenados pela data de criação (`desc`).

### 3. Integridade e Relacionamentos
- Relacionamento `@ManyToOne` entre Tópico e Autor/Curso.
- Relacionamento `@OneToMany` entre Tópico e Respostas.
- Uso de **Records** para DTOs, garantindo imutabilidade no tráfego de dados.

---

## 🚀 Como Executar

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.x
- MySQL Server

### Passo 1: Configurar o Banco de Dados
Crie o banco de dados e as tabelas necessárias:
```sql
CREATE DATABASE forumhub_api;
```
Passo 2: Configurar o application.properties
Ajuste as credenciais de acesso ao seu MySQL em src/main/resources/application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configuração para estabilidade do JSON de Paginação
spring.data.web.pageable.serialization-mode=via_dto
```

Passo 3: Rodar a Aplicação

Bash: mvn spring-boot:run

📖 Documentação dos Endpoints

| Método | Endpoint | Descrição | Parâmetros de URL |
| :--- | :--- | :--- | :--- |
| **POST** | `/topicos` | Cria um tópico | - |
| **GET** | `/topicos` | Lista todos (Paginado) | `page`, `size`, `sort` |
| **GET** | `/topicos/{id}` | Detalha um tópico | `id` |
| **PUT** | `/topicos/{id}` | Atualiza título/mensagem | `id` |
| **DELETE** | `/topicos/{id}` | Remove um tópico | `id` |

### 👤 Usuários (Autores)
| Método | Endpoint | Descrição | Parâmetros |
| :--- | :--- | :--- | :--- |
| **POST** | `/usuarios` | Cadastra um novo autor | - |
| **GET** | `/usuarios` | Lista todos os usuários | - |
| **GET** | `/usuarios/{id}` | Detalha perfil do usuário | `id` |
| **DELETE** | `/usuarios/{id}` | Remove um usuário | `id` |

### 💬 Respostas
| Método | Endpoint | Descrição | Parâmetros |
| :--- | :--- | :--- | :--- |
| **POST** | `/respostas` | Envia uma resposta em um tópico | - |
| **GET** | `/respostas/{id}` | Detalha uma resposta específica | `id` |
| **PUT** | `/respostas/{id}` | Edita o texto da resposta | `id` |
| **DELETE** | `/respostas/{id}` | Remove uma resposta | `id` |

## 📊 Modelos de Envio (JSON)

### 📝 Gerar Token
```
{
"username": "dev",
"password": "devpass"
}
```


### 📝 Criar Usuário
```json
{
    "nome": "Gabriel Pereira",
    "email": "gabriel.developer@email.com",
    "senha": "senha_segura_123"
}
```
### 📝Criar Resposta

```JSON
{
"mensagem": "Excelente! O mapeamento JPA está funcionando.",
"topicoId": 1,
"autorId": 1
}
```
### 📝 Criar Novo Tópico

```json
{
    "titulo": "Dúvida em Spring Data JPA",
    "mensagem": "Como implementar a paginação corretamente no Controller?",
    "autorId": 1,
    "cursoId": 1
}
```
### 📝 Respostas

```JSON
{
"mensagem": "Basta usar o parâmetro Pageable no método do seu Controller!",
"topicoId": 1,
"autorId": 1
}
```

👤 Autor
Desenvolvido por Gabriel Pereira.
Aluno do Oracle Next Education Desenvolvedor Backend em Java.

Este projeto foi desenvolvido como parte do Challenge Fórum Hub da Alura.