package produto.api.adapters.in.service;

import produto.api.adapters.in.dto.ReservaRequestDto;
import produto.api.adapters.in.dto.ReservaResponseDto;

public interface ReservaService {

    ReservaResponseDto reservaProduto (ReservaRequestDto request, String token);

}
