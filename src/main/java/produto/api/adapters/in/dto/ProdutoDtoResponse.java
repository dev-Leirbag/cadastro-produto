package produto.api.adapters.in.dto;

import java.math.BigDecimal;

public record ProdutoDtoResponse(Long id, String nomeProduto, String tipoProduto, BigDecimal preco, Integer quantidadeEstoque){}
