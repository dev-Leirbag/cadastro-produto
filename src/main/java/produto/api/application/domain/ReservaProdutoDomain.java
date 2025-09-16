package produto.api.application.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class ReservaProdutoDomain {

    private Long reserva_id;
    private String email_usuario;
    private BigDecimal valor_total;

    public ReservaProdutoDomain(Long reserva_id, String email_usuario, BigDecimal valor_total) {
        this.reserva_id = reserva_id;
        this.email_usuario = email_usuario;
        this.valor_total = valor_total;
    }

    public ReservaProdutoDomain() {
    }

    public Long getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(Long reserva_id) {
        this.reserva_id = reserva_id;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValor_total(BigDecimal valor_total) {
        this.valor_total = valor_total;
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
