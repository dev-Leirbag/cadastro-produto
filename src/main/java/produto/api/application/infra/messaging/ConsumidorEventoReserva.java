package produto.api.application.infra.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorEventoReserva {

    private static final Logger logger = LoggerFactory.getLogger(ConsumidorEventoReserva.class);

    @RabbitListener(queues = RabbitConfig.FILA_RESERVA_PRODUTO)
    public void consumirEventoReserva(EventoReservaProduto evento){
        logger.info("📩 Evento de reserva recebido do RabbitMQ:");
        logger.info("Usuario: ", evento.getEmailUsuario());
        logger.info("ReservaId: ", evento.getReservaId());
        logger.info("Produtos: ", evento.getProdutos());
        logger.info("ValorTotal: ", evento.getValorTotal());
    }
}
