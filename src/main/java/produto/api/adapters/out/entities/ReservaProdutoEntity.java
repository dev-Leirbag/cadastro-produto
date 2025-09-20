package produto.api.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_reserva")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservaProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserva_id;

    @Column(name = "email_usuario")
    private String emailUsuario;
    private BigDecimal valor_total;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaItemEntity> itens = new ArrayList<>();

}
