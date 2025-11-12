# User Manager API

API REST para gerenciamento de usuários desenvolvida com Spring Boot.

## Pré-requisitos

- Java 25 ou superior

## Como Executar

### Opção 1: Via Maven
```bash
mvn spring-boot:run
```

### Opção 2: Via IDE
Execute a classe `UserManagerApplication.java`

## Endpoints

A API estará rodando em `http://localhost:8080`

### Endpoints Disponíveis

- `POST /api/v1/user` - Criar usuário
- `GET /api/v1/user` - Listar todos os usuários
- `GET /api/v1/user/{id}` - Buscar usuário por ID
- `PUT /api/v1/user/{id}` - Atualizar usuário
- `DELETE /api/v1/user/{id}` - Deletar usuário

Após iniciar a aplicação, acesse o Swagger:

- **Swagger UI**: http://localhost:8080/swagger-ui.html


