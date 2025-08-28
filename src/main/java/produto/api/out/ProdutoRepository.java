package produto.api.out;


import jakarta.transaction.Transactional;
import produto.api.application.domain.ProdutoDomain;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    ProdutoDomain salvaProduto(ProdutoDomain domain);

    boolean existisByProduto(String nomeProduto);

    List<ProdutoDomain> listaProduto();

    Optional<ProdutoDomain> findById(Long id);

    ProdutoDomain atualizaProduto(ProdutoDomain domain);

    @Transactional
    void deletaProduto(ProdutoDomain domain);

    List<ProdutoDomain> buscarProdutoPorNome(String nome);
}
