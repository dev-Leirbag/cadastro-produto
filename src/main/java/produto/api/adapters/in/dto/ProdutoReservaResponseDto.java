package produto.api.adapters.in.dto;

import java.math.BigDecimal;

public record ProdutoReservaResponseDto(Long reservaId, String emailUsuario, BigDecimal valorTotal) {
}
