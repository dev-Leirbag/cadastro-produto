package produto.api.adapters.in.dto;

import java.util.List;

public record ReservaRequestDto(List<ReservaItemsDto> produtos) {
}
