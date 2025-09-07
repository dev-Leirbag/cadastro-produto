package produto.api.adapters.out.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.out.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoJpaRepository jpaRepository;
    private final Converter converter;

    @Override
    public ProdutoDomain salvaProduto(ProdutoDomain domain) {
        ProdutoEntity produtoEntity = converter.domainParaEntity(domain);

        ProdutoEntity produtoSalvo = jpaRepository.save(produtoEntity);

        return converter.entityParaDomain(produtoSalvo);
    }

    @Override
    public boolean existisByProduto(String nomeProduto) {
        return jpaRepository.existsByNomeProduto(nomeProduto);
    }

    @Override
    public Page<ProdutoDomain> listaProduto(Pageable pageable) {
        Page<ProdutoEntity> produtoEntityList = jpaRepository.findAll(pageable);

        return produtoEntityList.map(converter::entityParaDomain);
    }

    @Override
    public Optional<ProdutoDomain> findById(Long id) {
        return jpaRepository.findById(id).map(converter::entityParaDomain);
    }

    @Override
    public ProdutoDomain atualizaProduto(ProdutoDomain domain) {
        ProdutoEntity produtoEntity = converter.domainParaEntity(domain);

        ProdutoEntity produtoSalvo = jpaRepository.save(produtoEntity);

        return converter.entityParaDomain(produtoSalvo);
    }

    @Override
    public void deletaProduto(ProdutoDomain domain) {
        ProdutoEntity produtoEntity = converter.domainParaEntity(domain);

        jpaRepository.delete(produtoEntity);
    }

    @Override
    public Page<ProdutoDomain> buscarProdutoPorNome(Pageable pageable,String nome) {
        Page<ProdutoEntity> produtoEntityList = jpaRepository.buscaPorNomeProduto(pageable,nome);

        return produtoEntityList.map(converter::entityParaDomain);
    }

    @Override
    public Page<ProdutoDomain> buscaProdutoPorTipo(Pageable pageable,String tipo) {
        Page<ProdutoEntity> produtoEntityList = jpaRepository.buscaProdutoPorTipo(pageable,tipo);

        return produtoEntityList.map(converter::entityParaDomain);
    }

    @Override
    public Page<ProdutoDomain> buscaPorPreco(Pageable pageable,BigDecimal min, BigDecimal max) {
        Page<ProdutoEntity> produtoEntityList = jpaRepository.buscaPorPreco(pageable,min, max);

        return produtoEntityList.map(converter::entityParaDomain);
    }

    @Override
    public List<ProdutoDomain> buscaAvancada(String nomeProduto, String tipoProduto, BigDecimal min, BigDecimal max) {
        List<ProdutoEntity> produtoEntityList = jpaRepository.buscaAvancada(nomeProduto, tipoProduto, min, max);

        return converter.entityParaDomain(produtoEntityList);
    }
}
