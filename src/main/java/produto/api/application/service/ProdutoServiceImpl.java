package produto.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import produto.api.adapters.in.dto.ProdutoDtoRequest;
import produto.api.adapters.in.dto.ProdutoDtoResponse;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.in.mapper.UpdateConverter;
import produto.api.adapters.in.service.ProdutoService;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.application.infra.controller.exceptions.*;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final Converter converter;
    private final UpdateConverter updateConverter;
    private final ProdutoRepository repository;

    @Override
    public ProdutoDtoRequest criaProduto(ProdutoDtoRequest request) {
        ProdutoDomain produtoDomain = converter.dtoRequestParaDomain(request);

        verificaCampos(produtoDomain);

        repository.salvaProduto(produtoDomain);

        return converter.domainParaDtoRequest(produtoDomain);
    }

    @Override
    public List<ProdutoDtoResponse> listaProduto(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProdutoDomain> domainList = repository.listaProduto(pageable);

        verificaLista(domainList);

        return domainList.map(converter::domainParaDtoResponse).toList();
    }

    @Override
    public ProdutoDtoResponse buscaProdutoPorId(Long id) {
        ProdutoDomain produtoDomain = repository.findById(id).orElseThrow(() -> {
            throw new ProdutoNotFoundException("Produto com esse id não foi encontrado");
        });

        return converter.domainParaDtoResponse(produtoDomain);
    }

    @Override
    public ProdutoDtoRequest atualizaProdutoPorId(ProdutoDtoRequest data, Long id) {
        ProdutoDomain produtoDomain = repository.findById(id).orElseThrow(() -> {
            throw new ProdutoNotFoundException("Produto com esse id não foi encontrado");
        });

        ProdutoEntity produtoEntity = converter.domainParaEntity(produtoDomain);

        updateConverter.updateConverter(data, produtoEntity);

        ProdutoDomain produtoAtualizado = repository.atualizaProduto(
                converter.entityParaDomain(produtoEntity));

        return converter.domainParaDtoRequest(produtoAtualizado);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        ProdutoDomain produtoDomain = repository.findById(id).orElseThrow(() -> {
            throw new ProdutoNotFoundException("Produto com esse id não foi encontrado");
        });

        repository.deletaProduto(produtoDomain);
    }

    @Override
    public List<ProdutoDtoResponse> buscaProduto(String nomeProduto) {
            List<ProdutoDomain> domainList = repository.buscarProdutoPorNome(nomeProduto);

            return converter.domainParaDtoResponse(domainList);
    }

    @Override
    public List<ProdutoDtoResponse> buscaPorTipoProduto(String tipoProduto) {
        List<ProdutoDomain> domainList = repository.buscaProdutoPorTipo(tipoProduto);

        return converter.domainParaDtoResponse(domainList);
    }

    @Override
    public List<ProdutoDtoResponse> buscaPorPreco(BigDecimal min, BigDecimal max) {
        List<ProdutoDomain> domainList = repository.buscaPorPreco(min, max);

        return converter.domainParaDtoResponse(domainList);
    }

    @Override
    public List<ProdutoDtoResponse> buscaAvancada(String nomeProduto, String tipoProduto, BigDecimal min, BigDecimal max) {
        List<ProdutoDomain> domainList = repository.buscaAvancada(nomeProduto, tipoProduto, min, max);

        return converter.domainParaDtoResponse(domainList);
    }

    private void verificaLista(Page<ProdutoDomain> domainList) {
        if (domainList.isEmpty()) throw new ProdutoNotFoundException("Nenhum produto encontrado");
    }

    private void verificaCampos(ProdutoDomain produto) {
        String nomeProduto = produto.getNomeProduto();
        String tipoProduto = produto.getTipoProduto();
        BigDecimal preco = produto.getPreco();
        Integer quantidadeEstoque = produto.getQuantidadeEstoque();

        if (nomeProduto.isBlank()) throw new NomeProdutoInvalidException("O nome do produto não pode estar vazio!");
        if (repository.existisByProduto(nomeProduto)) throw new ProdutoExistsException("Esse produto já existe");

        if (tipoProduto.isBlank()) throw new TipoProdutoInvalidException("O tipo do produto não pode estar vazio!");

        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new PrecoInvalidException("O preço não pode ser nulo ou abaixo de zero");

        if (quantidadeEstoque == null || quantidadeEstoque <= 0)
            throw new QuantidadeEstoqueInvalidException("A quantidade não pode ser nula ou abaixo de zero");
    }
}
