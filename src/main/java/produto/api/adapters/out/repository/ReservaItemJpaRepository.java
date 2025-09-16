package produto.api.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import produto.api.adapters.out.entities.ReservaItemEntity;

public interface ReservaItemJpaRepository extends JpaRepository<ReservaItemEntity, Long> {
}
