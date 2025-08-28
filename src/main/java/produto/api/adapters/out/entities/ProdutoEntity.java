package produto.api.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity()
@Table(name = "tb_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;

    private String tipoProduto;

    private BigDecimal preco;

    private Integer quantidadeEstoque;

    public ProdutoEntity(ProdutoEntity data){
        this.id = data.getId();
        this.nomeProduto = data.getNomeProduto();
        this.tipoProduto = data.getTipoProduto();
        this.preco = data.getPreco();
        this.quantidadeEstoque = data.getQuantidadeEstoque();
    }
}
