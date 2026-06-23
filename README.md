```markdown
# User Management API

REST API para gerenciamento de usuários com autenticação JWT, desenvolvida como projeto de portfólio.

## Tecnologias

- **Java** + **Spring Boot 4**
- **Spring Security** com autenticação **JWT**
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL** (via Docker)
- **Springdoc OpenAPI** (Swagger UI)
- **Docker** + **Docker Compose**

## Funcionalidades

- Registro e login de usuários com geração de token JWT
- Senhas criptografadas com BCrypt
- Rotas protegidas por autenticação via token
- CRUD completo de usuários
- DTOs para não expor dados sensíveis nas respostas
- Documentação interativa via Swagger UI

## Como rodar localmente

### Pré-requisitos

- Java 17+
- Maven
- Docker e Docker Compose

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/user-management-api.git
cd user-management-api
```

### 2. Suba o banco de dados

```bash
docker compose up -d
```

### 3. Rode a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Endpoints

### Autenticação (público)

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/auth/register` | Registrar novo usuário |
| POST | `/auth/login` | Login e obtenção do token JWT |

**Exemplo — registro:**
```json
{
  "nome": "Maria Rita",
  "username": "maria",
  "email": "maria@email.com",
  "senha": "123456"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Usuários (requer autenticação)

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/usuarios` | Listar todos os usuários |
| GET | `/usuarios/{id}` | Buscar usuário por ID |
| PUT | `/usuarios/{id}` | Atualizar usuário |
| DELETE | `/usuarios/{id}` | Deletar usuário |

Para acessar as rotas protegidas, envie o token no header de cada requisição:

```
Authorization: Bearer <seu_token>
```

## Documentação

Com a aplicação rodando, acesse o Swagger UI:

```
http://localhost:8080/swagger-ui.html
```
