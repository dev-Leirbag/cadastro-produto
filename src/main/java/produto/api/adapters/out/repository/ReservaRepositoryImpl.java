package produto.api.adapters.out.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import produto.api.adapters.in.mapper.Converter;
import produto.api.adapters.out.entities.ReservaProdutoEntity;
import produto.api.out.ReservaRepository;

@Repository
@RequiredArgsConstructor
public class ReservaRepositoryImpl implements ReservaRepository {

    private final ReservaJpaRepository jpaRepository;
    private final Converter converter;

    @Override
    public ReservaProdutoEntity salvaReserva(ReservaProdutoEntity entity) {
        ReservaProdutoEntity entitySalvo = jpaRepository.save(entity);

        return entitySalvo;
    }

    @Override
    public ReservaProdutoEntity findByEmailUsuario(String email) {
        ReservaProdutoEntity entity = jpaRepository.findByEmailUsuario(email);

        return entity;
    }

    @Override
    public ReservaProdutoEntity findByReservaId(Long id) {
        ReservaProdutoEntity entity = jpaRepository.findByReservaId(id);

        return entity;
    }
}
