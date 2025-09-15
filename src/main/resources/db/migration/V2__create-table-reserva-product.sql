CREATE TABLE tb_reserva_produto(
    reserva_id INTEGER PRIMARY KEY,
    produto_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    usuario_id INTEGER NOT NULL
);