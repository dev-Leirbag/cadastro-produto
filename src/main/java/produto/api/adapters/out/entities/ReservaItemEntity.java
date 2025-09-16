package produto.api.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_reserva_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private ReservaProdutoEntity reserva;

    private Long produto_id;
    private String nome_produto;
    private String tipo_produto;
    private Integer quantidade;
    private BigDecimal preco_unitario;
    private BigDecimal valor_total_item;

}
