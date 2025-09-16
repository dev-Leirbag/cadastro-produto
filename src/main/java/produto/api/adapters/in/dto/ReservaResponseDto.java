package produto.api.adapters.in.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReservaResponseDto(String emailUsuario, Long reservaId,
                                 List<ProdutoReservaDto> produtos, BigDecimal valor_total) {
}
