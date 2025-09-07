package produto.api.adapters.out.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import produto.api.adapters.out.entities.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long> {

    boolean existsByNomeProduto(String nomeProduto);

    @Query("SELECT p FROM ProdutoEntity p WHERE UPPER(p.nomeProduto) LIKE UPPER(concat('%', :nome, '%'))")
    Page<ProdutoEntity> buscaPorNomeProduto(Pageable pageable, @Param("nome")String nome);

    @Query("SELECT p FROM ProdutoEntity p WHERE UPPER(p.tipoProduto) LIKE UPPER(concat('%', :tipoProduto, '%'))")
    Page<ProdutoEntity> buscaProdutoPorTipo(Pageable pageable, @Param("tipoProduto") String tipoProduto);

    @Query("SELECT p FROM ProdutoEntity p WHERE p.preco >= :min AND p.preco <= :max")
    List<ProdutoEntity> buscaPorPreco(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("SELECT p FROM ProdutoEntity p WHERE " +
           "(:nomeProduto IS NULL OR UPPER(p.nomeProduto) LIKE UPPER(concat('%', :nomeProduto, '%'))) AND " +
           "(:tipoProduto IS NULL OR UPPER(p.tipoProduto) LIKE UPPER(concat('%', :tipoProduto, '%'))) AND " +
           "(:min IS NULL OR p.preco >= :min) AND " +
           "(:max IS NULL OR p.preco <= :max)")
    List<ProdutoEntity> buscaAvancada(@Param("nomeProduto") String nomeProduto, @Param("tipoProduto") String tipoProduto, @Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
