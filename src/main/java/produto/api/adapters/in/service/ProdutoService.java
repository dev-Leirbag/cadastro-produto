package produto.api.adapters.in.service;

import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;

import java.util.List;

public interface ProdutoService {

    ProdutoDtoRequest criaProduto (ProdutoDtoRequest request);

    List<ProdutoDtoResponse> listaProduto();
}
