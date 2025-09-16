CREATE TABLE tb_reserva_produto(
    reserva_id SERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    email_usuario VARCHAR(100) NOT NULL
);
