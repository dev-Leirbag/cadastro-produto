package produto.api.out;

import produto.api.adapters.out.entities.ReservaProdutoEntity;

public interface ReservaRepository {

    ReservaProdutoEntity salvaReserva(ReservaProdutoEntity entity);

    ReservaProdutoEntity findByEmailUsuario(String email);

    ReservaProdutoEntity findByReservaId (Long id);

}
