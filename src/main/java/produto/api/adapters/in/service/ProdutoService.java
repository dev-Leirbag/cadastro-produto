package produto.api.adapters.in.service;

import jakarta.transaction.Transactional;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    ProdutoDtoRequest criaProduto (ProdutoDtoRequest request);

    List<ProdutoDtoResponse> listaProduto(int page, int size);

    ProdutoDtoResponse buscaProdutoPorId(Long id);

    ProdutoDtoRequest atualizaProdutoPorId(ProdutoDtoRequest data,Long id);

    @Transactional
    void deletaProdutoPorId(Long id);

    List<ProdutoDtoResponse> buscaProduto(int page, int size,String nomeProduto);

    List<ProdutoDtoResponse> buscaPorTipoProduto(int page, int size,String tipoProduto);

    List<ProdutoDtoResponse> buscaPorPreco(BigDecimal min, BigDecimal max);

    List<ProdutoDtoResponse> buscaAvancada(String nomeProduto, String tipoProduto, BigDecimal min, BigDecimal max);
}
