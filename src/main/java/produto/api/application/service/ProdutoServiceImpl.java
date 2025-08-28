package produto.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.in.service.ProdutoService;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.NomeProdutoInvalidException;
import produto.api.application.infra.controller.exceptions.PrecoInvalidException;
import produto.api.application.infra.controller.exceptions.QuantidadeEstoqueInvalidException;
import produto.api.application.infra.controller.exceptions.TipoProdutoInvalidException;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final Converter converter;
    private final ProdutoRepository repository;

    @Override
    public ProdutoDtoRequest criaProduto(ProdutoDtoRequest request) {
        ProdutoDomain produtoDomain = converter.dtoRequestParaDomain(request);

        verificaCampos(produtoDomain);

        repository.salvaProduto(produtoDomain);

        return converter.domainParaDtoRequest(produtoDomain);
    }

    private void verificaCampos(ProdutoDomain produto){
        String nomeProduto = produto.getNomeProduto();
        String tipoProduto = produto.getTipoProduto();
        BigDecimal preco = produto.getPreco();
        Integer quantidadeEstoque = produto.getQuantidadeEstoque();

        if(nomeProduto.isBlank()) throw new NomeProdutoInvalidException("O nome do produto não pode estar vazio!");

        if(tipoProduto.isBlank()) throw new TipoProdutoInvalidException("O tipo do produto não pode estar vazio!");

        if(preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) throw new PrecoInvalidException("O preço não pode ser nulo ou abaixo de zero");

        if(quantidadeEstoque == null || quantidadeEstoque <= 0) throw new QuantidadeEstoqueInvalidException("A quantidade não pode ser nula ou abaixo de zero");
    }
}
