package produto.api.adapters.out.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto_reservado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoReservaEntity {

    private String emailUsuario;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Long idProduto;

    private String tipoProduto;

    private BigDecimal precoUnitario;

    private BigDecimal valorTotal;

    private Integer quantidade;

}
