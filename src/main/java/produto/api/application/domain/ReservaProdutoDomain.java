package produto.api.application.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ReservaProdutoDomain {

    private Long reserva_id;
    private Long produto_id;
    private Integer quantidade;
    private BigDecimal valor_total;
    private String email_usuario;

    public ReservaProdutoDomain(Long reserva_id, Long produto_id, Integer quantidade, BigDecimal valor_total, String email_usuario) {
        this.reserva_id = reserva_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.valor_total = valor_total;
        this.email_usuario = email_usuario;
    }

    public ReservaProdutoDomain() {
    }


    public Long getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReservaProdutoDomain that)) return false;
        return Objects.equals(getReserva_id(), that.getReserva_id());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getReserva_id());
    }
}
