CREATE TABLE tb_produto_reservado (
    email_usuario VARCHAR(255) PRIMARY KEY,
    id_Produto SERIAL NOT NULL,
    tipo_produto VARCHAR(255) NOT NULL,
    preco_unitario DECIMAL(12,2) NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES tb_produto(id),

);