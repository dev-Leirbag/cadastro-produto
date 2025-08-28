package produto.api.out;


import produto.api.application.domain.ProdutoDomain;

public interface ProdutoRepository {

    ProdutoDomain salvaProduto(ProdutoDomain domain);
}
