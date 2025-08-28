package produto.api.adapters.out.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.out.entities.ProdutoEntity;
import produto.api.application.domain.ProdutoDomain;
import produto.api.out.ProdutoRepository;

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
    public List<ProdutoDomain> listaProduto() {
        List<ProdutoEntity> produtoEntityList = jpaRepository.findAll();

        return converter.entityParaDomain(produtoEntityList);
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
}
