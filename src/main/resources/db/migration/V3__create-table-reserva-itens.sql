CREATE TABLE tb_reserva_item(
    id SERIAL PRIMARY KEY,
    reserva_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    nome_Produto VARCHAR(255),
    tipo_Produto VARCHAR(255),
    quantidade INTEGER,
    preco_unitario DECIMAL(12,2) NOT NULL,
    valor_total_item DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (reserva_id) REFERENCES tb_reserva(reserva_id)
);