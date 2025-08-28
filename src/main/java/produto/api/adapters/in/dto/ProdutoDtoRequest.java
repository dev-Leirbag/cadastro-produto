package produto.api.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDtoRequest {

    private String nomeProduto;
    private String tipoProduto;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
}
