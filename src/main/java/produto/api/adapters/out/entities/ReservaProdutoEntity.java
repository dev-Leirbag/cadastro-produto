package produto.api.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_reserva_produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservaProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserva_id;

    private Long produto_id;

    private Integer quantidade;

    private BigDecimal valor_total;

    private String email_usuario;
}
