# API de Cadastro de Produtos

Este projeto é uma API REST para cadastro e gerenciamento de produtos,
construída com **Java 17 + Spring Boot**, utilizando **Spring Data
JPA**, **Flyway** para migrations e suporte a **Docker** e **Docker
Compose**.

------------------------------------------------------------------------

## 🚀 Tecnologias

-   Java 17
-   Spring Boot
-   Spring Data JPA
-   Flyway
-   Docker & Docker Compose
-   PostgreSQL
-   JUnit & Mockito (testes)

------------------------------------------------------------------------

## 📂 Estrutura do Projeto

-   `controller/` → Endpoints REST
-   `service/` → Regras de negócio
-   `repository/` → Integração com banco de dados
-   `domain/` → Objetos de negócio
-   `entity/` → Entidades persistidas
-   `converter/` → Conversão entre camadas
-   `exception/` → Exceções customizadas
-   `resources/db/migration/` → Scripts Flyway
-   `Dockerfile` → Configuração da imagem da aplicação
-   `docker-compose.yml` → Orquestração da aplicação + banco de dados

------------------------------------------------------------------------

## ⚙️ Configuração e Execução

### Pré-requisitos

-   **Java 17**
-   **Maven**
-   **Docker** e **Docker Compose**

### Rodando localmente (sem Docker)

1.  Clone o repositório:

    ``` bash
    git clone https://github.com/dev-Leirbag/cadastro-produto.git
    cd cadastro-produto
    ```

2.  Configure as credenciais do banco em `application.properties` (ou
    `application.yml`):

    ``` properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/produtos
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

3.  Inicie a aplicação:

    ``` bash
    mvn spring-boot:run
    ```

4.  Acesse os endpoints (padrão `http://localhost:9091/`):

    -   `GET /api/produto/all` → lista todos os produtos\
    -   `POST /api/produto` → cria produto\
    -   `PUT /api/produto/{id}` → atualiza produto\
    -   `DELETE /api/produto/{id}` → deleta produto\
    -   `GET /api/produto/buscar/filtro` → busca avançada (parâmetros
        opcionais: `nomeProduto`, `tipoProduto`, `min`, `max`)

------------------------------------------------------------------------

## 🐳 Rodando com Docker

1.  Gere o `.jar`:

    ``` bash
    mvn clean package
    ```

2.  Suba os containers com Docker Compose:

    ``` bash
    docker compose up --build
    ```

3.  O Spring leva alguns segundos para aplicar as migrations com Flyway
    e abrir o endpoint.

------------------------------------------------------------------------

## ✅ Testes

Para rodar os testes unitários:

``` bash
mvn test
```

Cobertura de testes inclui: - CRUD de produtos - Validação de exceções
(produto não encontrado) - Atualização parcial de atributos

------------------------------------------------------------------------

## 🔗 Endpoints de Exemplo

### Criar Produto

``` http
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

``` http
GET /api/produto/buscar/filtro?nomeProduto=note&min=1000&max=4000
```

------------------------------------------------------------------------

## 📌 Observações

-   A aplicação utiliza **Flyway** para versionamento do banco de dados.
-   O `docker-compose.yml` já sobe **PostgreSQL** e a aplicação juntos.
-   Em ambiente real, configurar variáveis de ambiente em vez de deixar
    credenciais fixas.

------------------------------------------------------------------------

## 👨‍💻 Autor

Projeto desenvolvido por **Gabriel (dev-Leirbag)** 🚀\
[GitHub](https://github.com/dev-Leirbag)
