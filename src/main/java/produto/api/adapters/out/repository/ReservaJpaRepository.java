package produto.api.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import produto.api.adapters.out.entities.ReservaProdutoEntity;

public interface ReservaJpaRepository extends JpaRepository<ReservaProdutoEntity, Long> {

    ReservaProdutoEntity findByEmailUsuario(String email);

    ReservaProdutoEntity findByReservaId (Long id);

}
