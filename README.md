# API de Cadastro de Produtos

Este projeto é uma API REST para **cadastro e gerenciamento de produtos**,
construída com **Java 17 + Spring Boot**, utilizando **Spring Data JPA**,
**Flyway** para migrations e suporte a **Docker** e **Docker Compose**.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Flyway
- Docker & Docker Compose
- PostgreSQL
- Swagger / OpenAPI (documentação e testes de endpoints)
- JUnit & Mockito (testes)

---

## 📂 Estrutura do Projeto

- `controller/` → Endpoints REST
- `service/` → Regras de negócio
- `repository/` → Integração com banco de dados
- `domain/` → Objetos de negócio
- `entity/` → Entidades persistidas
- `converter/` → Conversão entre camadas
- `exception/` → Exceções customizadas
- `resources/db/migration/` → Scripts Flyway
- `Dockerfile` → Configuração da imagem da aplicação
- `docker-compose.yml` → Orquestração da aplicação + banco de dados

---

## 🔗 Integração com Cadastro de Usuário

Este serviço funciona em conjunto com o projeto de **Cadastro de Usuário**,
disponível em:  
👉 [cadastro-usuario](https://github.com/dev-Leirbag/cadastro-usuario)

O fluxo de utilização integrado é o seguinte:

1. Suba ambos os containers com Docker (tanto `cadastro-usuario` quanto `cadastro-produto`).  
   - O serviço de **usuário** roda na porta `9090`.  
   - O serviço de **produtos** roda na porta `9091`.

2. Cadastre um usuário ou use o perfil **admin** (criado automaticamente ao subir via Docker no serviço de usuários).

3. Realize o login no serviço de **usuários** (`/login`) e obtenha o token JWT.

4. No Insomnia (ou Postman), configure a aba **Auth** da requisição para usar `Bearer Token`, cole o token obtido do login.  

5. Agora você pode acessar os endpoints de **reserva de produtos** e **gerenciamento de estoque**, que exigem autenticação.  
   - Sem token válido, o serviço de produtos **não autoriza** a reserva.

---

## 📖 Swagger (Documentação e Testes)

A aplicação expõe sua documentação de API via **Swagger UI**.  
Após iniciar o serviço, acesse:

```
http://localhost:9091/swagger-ui.html
```

No Swagger você pode:

- Visualizar todos os endpoints disponíveis
- Testar requisições diretamente pela interface
- Inserir o token JWT (via botão "Authorize") para chamar endpoints autenticados

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- **Java 17**
- **Maven**
- **Docker** e **Docker Compose**

### Rodando localmente (sem Docker)

1. Clone o repositório:

    ```bash
    git clone https://github.com/dev-Leirbag/cadastro-produto.git
    cd cadastro-produto
    ```

2. Configure as credenciais do banco em `application.properties` (ou `application.yml`):

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/produtos
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

3. Inicie a aplicação:

    ```bash
    mvn spring-boot:run
    ```

4. Acesse os endpoints (padrão `http://localhost:9091/`).

5. Acesse o Swagger em:  
   ```
   http://localhost:9091/swagger-ui.html
   ```

---

## 🐳 Rodando com Docker

1. Gere o `.jar`:

    ```bash
    mvn clean package
    ```

2. Suba os containers com Docker Compose:

    ```bash
    docker compose up --build
    ```

3. Certifique-se de que o container do serviço de **usuários** também está rodando (porta 9090).

4. O Spring leva alguns segundos para aplicar as migrations com Flyway e abrir o endpoint.

5. Teste a API pelo Swagger em:  
   ```
   http://localhost:9091/swagger-ui.html
   ```

---

## ✅ Testes

Para rodar os testes unitários:

```bash
mvn test
```

Cobertura de testes inclui:  
- CRUD de produtos  
- Validação de exceções (produto não encontrado)  
- Atualização parcial de atributos  

---

## 🔗 Endpoints de Exemplo

### Criar Produto

```http
POST /api/produto
Content-Type: application/json

{
  "nomeProduto": "Notebook",
  "tipoProduto": "Eletrônico",
  "preco": 3500.00,
  "quantidadeEstoque": 5
}
```

### Buscar com Filtros

```http
GET /api/produto/buscar/filtro?nomeProduto=note&min=1000&max=4000
```

### Reservar Produto (autenticado)

```http
POST /api/produto/reservar
Authorization: <seu_token_jwt> *Não é necessario passar o "Bearer " antes do token*

{
 "produtos" : [
        {
         "produtoId": 1,
          "quantidade": 2
        }
    ]  
}
```

**Resposta esperada (200 OK):**

```json
{
  "emailUsuario": "admin@admin.com",
	"reservaId": 1,
	"produtos": [
		{
			"id": 1,
			"nomeProduto": "...",
			"tipoProduto": "..."
		}
	],
    "valor_total" : "..."
}
```

---

## 📌 Observações

- A aplicação utiliza **Flyway** para versionamento do banco de dados.
- O `docker-compose.yml` já sobe **PostgreSQL** e a aplicação juntos.
- É necessário que o **cadastro-usuario** esteja rodando para autenticar as reservas.
- Apenas usuários autenticados (via JWT) podem reservar produtos.
- O perfil **admin** criado automaticamente pelo serviço de usuários pode gerenciar todos os recursos.
- O **Swagger UI** é a forma mais prática de testar os endpoints com ou sem autenticação.

---

## 👨‍💻 Autor

Projeto desenvolvido por **Gabriel (dev-Leirbag)** 🚀  
[GitHub](https://github.com/dev-Leirbag)
