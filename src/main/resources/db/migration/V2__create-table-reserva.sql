CREATE TABLE tb_reserva(
    reserva_id SERIAL PRIMARY KEY,
    email_usuario VARCHAR(100) NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL DEFAULT 0.00
);
