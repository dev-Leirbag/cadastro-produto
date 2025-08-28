package produto.api.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import produto.api.adapters.out.entities.ProdutoEntity;

import java.util.List;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long> {

    boolean existsByNomeProduto(String nomeProduto);

    @Query("SELECT p FROM ProdutoEntity p WHERE p.nomeProduto LIKE %:nome%")
    List<ProdutoEntity> buscaPorNomeProduto(@Param("nome")String nome);
}
