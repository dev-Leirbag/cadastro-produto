package produto.api.application.infra.messaging;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import produto.api.adapters.in.dto.ProdutoEventoDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//Essa classe representa o que vai ser enviado para a fila
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventoReservaProduto implements Serializable {

    private String emailUsuario;
    private Long reservaId;
    private BigDecimal valorTotal;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataReserva;

    private List<ProdutoEventoDto> produtos;

    @Override
    public String toString() {
        return "EventoReservaProduto{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", reservaId=" + reservaId +
                ", valorTotal=" + valorTotal +
                ", dataReserva=" + dataReserva +
                ", produtos=" + produtos +
                '}';
    }
}
