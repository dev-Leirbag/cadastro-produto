CREATE TABLE tb_produto(
      id SERIAL PRIMARY KEY,
      nome_produto VARCHAR(255) NOT NULL UNIQUE,
      tipo_produto VARCHAR(255) NOT NULL,
      preco DECIMAL(10,2) NOT NULL,
      quantidade_estoque INTEGER NOT NULL
);