package produto.api.adapters.in.dto;

import java.math.BigDecimal;

public record ProdutoReservaDtoResponse(String emailUsuario, Long idProduto,
                                        String tipoProduto, BigDecimal precoUnitario,
                                        BigDecimal valorTotal, Integer quantidade) {
}
