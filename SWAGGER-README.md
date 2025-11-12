# Documentação Swagger - User Manager API

Este projeto inclui documentação Swagger/OpenAPI completa para a API de gerenciamento de usuários.

## 📋 Índice

- [Configuração](#configuração)
- [Acessando a Documentação](#acessando-a-documentação)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Arquivos Criados](#arquivos-criados)

## ⚙️ Configuração

### Dependências

A dependência do SpringDoc OpenAPI foi adicionada ao `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

### Arquivos de Configuração

1. **OpenApiConfig.java**: Classe de configuração que define as informações básicas da API (título, versão, descrição, servidores, etc.)

2. **swagger.yaml**: Especificação OpenAPI 3.0 completa em formato YAML com todos os endpoints, schemas e exemplos

3. **Anotações no Controller**: O `UserController` foi anotado com `@Tag`, `@Operation`, `@ApiResponses` e `@Parameter` para documentação automática

4. **Anotações nos DTOs**: Os DTOs `UserRequest` e `UserData` foram anotados com `@Schema` para melhor documentação dos modelos

## 🌐 Acessando a Documentação

Após iniciar a aplicação Spring Boot, a documentação Swagger estará disponível em:

### Swagger UI (Interface Interativa)
```
http://localhost:8080/swagger-ui.html
```
ou
```
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI JSON
```
http://localhost:8080/v3/api-docs
```

### OpenAPI YAML
```
http://localhost:8080/v3/api-docs.yaml
```

## 📡 Endpoints Disponíveis

### 1. Criar Usuário
- **Método**: `POST`
- **URL**: `/api/v1/user`
- **Descrição**: Cria um novo usuário no sistema
- **Body**: 
  ```json
  {
    "name": "João Silva",
    "email": "joao.silva@example.com"
  }
  ```

### 2. Listar Todos os Usuários
- **Método**: `GET`
- **URL**: `/api/v1/user`
- **Descrição**: Retorna uma lista com todos os usuários cadastrados

### 3. Buscar Usuário por ID
- **Método**: `GET`
- **URL**: `/api/v1/user/{id}`
- **Descrição**: Retorna os dados de um usuário específico
- **Parâmetro**: `id` (Long) - ID do usuário

### 4. Atualizar Usuário
- **Método**: `PUT`
- **URL**: `/api/v1/user/{id}`
- **Descrição**: Atualiza os dados de um usuário existente
- **Parâmetro**: `id` (Long) - ID do usuário
- **Body**: 
  ```json
  {
    "name": "João Silva Atualizado",
    "email": "joao.silva.novo@example.com"
  }
  ```

### 5. Deletar Usuário
- **Método**: `DELETE`
- **URL**: `/api/v1/user/{id}`
- **Descrição**: Remove um usuário do sistema
- **Parâmetro**: `id` (Long) - ID do usuário

## 📁 Arquivos Criados

1. **`src/main/java/com/hascode88/user/config/OpenApiConfig.java`**
   - Classe de configuração do SpringDoc OpenAPI
   - Define informações da API, servidores e contatos

2. **`swagger.yaml`**
   - Especificação OpenAPI 3.0 completa
   - Pode ser importada em ferramentas como Postman, Insomnia, etc.
   - Contém todos os endpoints, schemas, exemplos e respostas

3. **Modificações em `UserController.java`**
   - Adicionadas anotações Swagger para documentação automática
   - Cada endpoint possui descrições e exemplos de respostas

4. **Modificações em `UserRequest.java` e `UserData.java`**
   - Adicionadas anotações `@Schema` para documentação dos modelos

## 🚀 Como Usar

1. **Compile e execute a aplicação**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

2. **Acesse o Swagger UI**:
   Abra seu navegador em `http://localhost:8080/swagger-ui.html`

3. **Teste os endpoints**:
   - Use a interface interativa do Swagger UI para testar todos os endpoints
   - Os exemplos de requisição já estão pré-configurados
   - Você pode executar requisições diretamente pela interface

## 📝 Notas

- A documentação é gerada automaticamente a partir das anotações no código
- O arquivo `swagger.yaml` pode ser usado para importação em outras ferramentas
- Todas as validações e regras de negócio estão documentadas
- Os códigos de resposta HTTP estão especificados para cada endpoint

## 🔧 Personalização

Para personalizar a documentação, edite:

- **Informações gerais**: `OpenApiConfig.java`
- **Detalhes dos endpoints**: Anotações no `UserController.java`
- **Modelos de dados**: Anotações nos DTOs
- **Especificação completa**: `swagger.yaml`

