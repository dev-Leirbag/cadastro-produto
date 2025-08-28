package produto.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.in.service.ProdutoService;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.*;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<ProdutoDtoResponse> listaProduto() {
        List<ProdutoDomain> domainList = repository.listaProduto();

        return converter.domainParaDtoResponse(domainList);
    }

    private void verificaCampos(ProdutoDomain produto){
        String nomeProduto = produto.getNomeProduto();
        String tipoProduto = produto.getTipoProduto();
        BigDecimal preco = produto.getPreco();
        Integer quantidadeEstoque = produto.getQuantidadeEstoque();

        if(nomeProduto.isBlank()) throw new NomeProdutoInvalidException("O nome do produto não pode estar vazio!");
        if(repository.existisByProduto(nomeProduto)) throw new ProdutoExistsException("Esse produto já existe");

        if(tipoProduto.isBlank()) throw new TipoProdutoInvalidException("O tipo do produto não pode estar vazio!");

        if(preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) throw new PrecoInvalidException("O preço não pode ser nulo ou abaixo de zero");

        if(quantidadeEstoque == null || quantidadeEstoque <= 0) throw new QuantidadeEstoqueInvalidException("A quantidade não pode ser nula ou abaixo de zero");
    }
}
