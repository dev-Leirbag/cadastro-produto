package produto.api.adapters.in.dto;

import java.math.BigDecimal;

public record ProdutoReservaDto(Long id, String nomeProduto, String tipoProduto,
                                Integer quantidadeSolicitada, BigDecimal precoUnitario, BigDecimal valorTotalItem) {
}
