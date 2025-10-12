package produto.api.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEventoDto implements Serializable {

    private Long idProduto;
    private String nomeProduto;
    private String tipoProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorTotalItem;

    @Override
    public String toString() {
        return "ProdutoEventoDto{" +
                "idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", tipoProduto='" + tipoProduto + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", valorTotalItem=" + valorTotalItem +
                '}';
    }
}
