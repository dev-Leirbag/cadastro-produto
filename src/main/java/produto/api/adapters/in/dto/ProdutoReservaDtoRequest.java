package produto.api.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoReservaDtoRequest {
    private String emailUsuario;
    private List<ProdutoDtoRequest> produtos;
}
