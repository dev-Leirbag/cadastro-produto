package produto.api.out;


import produto.api.application.domain.ProdutoDomain;

import java.util.List;

public interface ProdutoRepository {

    ProdutoDomain salvaProduto(ProdutoDomain domain);

    boolean existisByProduto(String nomeProduto);

    List<ProdutoDomain> listaProduto();
}
